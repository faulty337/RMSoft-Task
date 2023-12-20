package com.example.rmsofttask.LoanRecords;


import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Books.BooksRepository;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordsDto;
import com.example.rmsofttask.Users.Users;
import com.example.rmsofttask.Users.UsersRepository;
import com.example.rmsofttask.common.response.CustomException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanRecordsServiceTest {
    @MockBean
    private BooksRepository booksRepository;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private LoanRecordsRepository loanRecordsRepository;

    @Autowired
    private LoanRecordsService loanRecordsService;

    private static Long bookId;
    private static Books mockBook;
    private static Users mockUser;

    @BeforeAll
    static void setUP(){
        bookId = 1L;
        mockBook = new Books(bookId, "어린왕자", "생텍쥐페리", BookStatus.AVAILABLE);
        mockUser = new Users("faulty337", "김범준", "faulty337@gmail.com", "010-0000-0000", "qwer1234");
    }

    @Test
    @DisplayName("대출이력 조회 테스트")
    public void testGetLoanRecords(){
        int count = 15;
        List<LoanRecords> mockLoanRecordsList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            mockLoanRecordsList.add(new LoanRecords(mockBook, mockUser, LocalDate.now(), LocalDate.now()));
        }

        when(booksRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
        when(loanRecordsRepository.findByBooks(mockBook)).thenReturn(mockLoanRecordsList);

        LoanRecordResDto result = loanRecordsService.getLoanRecords(bookId);

        assertNotNull(result);
        assertEquals(bookId, result.getBookId());
        assertEquals(count, result.getLoanRecordsDtoList().size());
    }

    @Test
    @DisplayName("대출 신청 테스트")
    public void testCheckoutBook() {

        when(booksRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
        when(usersRepository.findById(mockUser.getUserId())).thenReturn(Optional.of(mockUser));
        when(loanRecordsRepository.findAllByUsersAndReturnedDateIsNull(mockUser)).thenReturn(Collections.emptyList());

        LoanRecordsDto result = loanRecordsService.checkoutBook(bookId, mockUser.getUserId());

        assertNotNull(result);
    }


    @Test
    @DisplayName("대출 권수 초과 테스트")
    public void testLoanExceedingMaxBooksLimit() {
        when(loanRecordsRepository.findAllByUsersAndReturnedDateIsNull(mockUser)).thenReturn(Collections.nCopies(11, new LoanRecords()));

        assertThrows(CustomException.class, () -> {
            loanRecordsService.checkoutBook(bookId, mockUser.getUserId());
        });
    }

    @Test
    @DisplayName("연체 테스트")
    public void testLoanExceedingMaxDayLimit() {
        when(loanRecordsRepository.findAllByUsersAndReturnedDateIsNull(mockUser)).thenReturn(List.of(new LoanRecords(mockBook, mockUser, LocalDate.now().minusDays(16), null)));

        assertThrows(CustomException.class, () -> {
            loanRecordsService.checkoutBook(bookId, mockUser.getUserId());
        });
    }


    @Test
    @DisplayName("반납 테스트")
    public void testReturnBook(){
        long bookId = 2L;
        Books mockBook = new Books(bookId, "어린왕자", "생텍쥐페리", BookStatus.CHECKED_OUT);
        LoanRecords mockLoanRecord = new LoanRecords(mockBook, mockUser, LocalDate.now(), null);

        when(booksRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
        when(loanRecordsRepository.findByBooksAndReturnedDateIsNull(mockBook)).thenReturn(Optional.of(mockLoanRecord));

        BookReturnResDto result = loanRecordsService.returnBook(bookId);

        assertNotNull(result);
        assertEquals(BookStatus.AVAILABLE, mockBook.getStatus());
        assertNotNull(mockLoanRecord.getReturnedDate());
    }

}
