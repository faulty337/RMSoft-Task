package com.example.rmsofttask.LoanRecords;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanRecordsService {
    private final LoanRecordsRepository loanRecordsRepository;
    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;


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


        LoanRecords loanRecords = new LoanRecords(book, user);

        loanRecordsRepository.save(loanRecords);

        return new LoanRecordsDto(loanRecords);
    }
}
