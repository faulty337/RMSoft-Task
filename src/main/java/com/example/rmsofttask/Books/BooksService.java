package com.example.rmsofttask.Books;

import com.example.rmsofttask.Books.dto.BookRegistrationReqDto;
import com.example.rmsofttask.Books.dto.BookRegistrationResDto;
import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;

    @Transactional
    public BookRegistrationResDto registerBook(String title, String author) {
        Books books = booksRepository.save(new Books(title, author, BookStatus.AVAILABLE));
        return new BookRegistrationResDto(books.getId(), books.getTitle(), books.getAuthor(), books.getStatus());
    }

    @Transactional
    public BookUpdateDto updateBook(Long bookId, String title, String author) {
        Books book = booksRepository.findById(bookId).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOND_BOOK_ID)
        );
        book.updateTitle(title);
        book.updateAuthor(author);

        return new BookUpdateDto(book.getId(), book.getTitle(), book.getAuthor());
    }
}
