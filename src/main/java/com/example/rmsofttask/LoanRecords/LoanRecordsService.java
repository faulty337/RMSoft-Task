package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Books.BooksRepository;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordsDto;
import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanRecordsService {
    private final LoanRecordsRepository loanRecordsRepository;
    private final BooksRepository booksRepository;
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
}
