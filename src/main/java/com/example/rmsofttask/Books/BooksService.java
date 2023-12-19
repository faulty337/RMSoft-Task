package com.example.rmsofttask.Books;

import com.example.rmsofttask.Books.dto.BookRegistrationReqDto;
import com.example.rmsofttask.Books.dto.BookRegistrationResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;

    public BookRegistrationResDto registerBook(String title, String author) {
        Books books = booksRepository.save(new Books(title, author, BookStatus.AVAILABLE));
        return new BookRegistrationResDto(books.getTitle(), books.getAuthor(), books.getStatus());
    }
}
