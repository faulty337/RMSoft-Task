package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Books.BooksRepository;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordsDto;
import com.example.rmsofttask.Users.Users;
import com.example.rmsofttask.Users.UsersRepository;
import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanRecordsService {
    private final LoanRecordsRepository loanRecordsRepository;
    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;

    private final Integer MAX_LOAN_COUNT_LIMIT = 10;
    private final Integer MAX_LOAN_DAY_LIMIT = 15;

    @Transactional(readOnly = true)
    public LoanRecordResDto getLoanRecords(Long bookId) {
        Books book = booksRepository.findById(bookId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOOK_ID)
        );

        List<LoanRecords> loanRecordsList =  loanRecordsRepository.findByBooks(book);

        return new LoanRecordResDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                loanRecordsList.stream().map(LoanRecordsDto::new).toList()
        );

    }


    @Transactional
    public LoanRecordsDto checkoutBook(Long bookId, String userId) {
        Books book = booksRepository.findById(bookId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOOK_ID)
        );
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        if(book.getStatus() != BookStatus.AVAILABLE){
            throw new CustomException(ErrorCode.BOOK_NOT_AVAILABLE);
        }

        List<LoanRecords> userByLoanRecordList = loanRecordsRepository.findAllByUsersAndReturnedDateIsNull(user);
        if(userByLoanRecordList.size() >= MAX_LOAN_COUNT_LIMIT){
            throw new CustomException(ErrorCode.MAX_CHECKOUT_EXCEEDED);
        }

        if(userByLoanRecordList.stream().anyMatch(loanRecords -> loanRecords.getCheckoutDate().plusDays(MAX_LOAN_DAY_LIMIT).isAfter(LocalDate.now()))){
            throw new CustomException(ErrorCode.OVERDUE_BOOKS_EXIST);
        };

        LoanRecords loanRecords = new LoanRecords(book, user);

        loanRecordsRepository.save(loanRecords);

        return new LoanRecordsDto(loanRecords);
    }
}
