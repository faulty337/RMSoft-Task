package com.example.rmsofttask.LoanRecords;


import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Books.BooksRepository;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.Users.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanRecordsServiceTest {
    @MockBean
    private BooksRepository booksRepository;

    @MockBean
    private LoanRecordsRepository loanRecordsRepository;

    @Autowired
    private LoanRecordsService loanRecordsService;

    @Test
    @DisplayName("대출이력 조회 테스트")
    public void testGetLoanRecords(){
        Long bookId = 1L;
        Books mockBook = new Books(bookId, "어린왕자", "생텍쥐페리", BookStatus.AVAILABLE);
        Users mockUser = new Users("faulty337", "김범준", "faulty337@gmail.com", "010-0000-0000", "qwer1234");
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
        // 기타 결과 검증...
    }

}
