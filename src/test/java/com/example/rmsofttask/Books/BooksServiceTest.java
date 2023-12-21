package com.example.rmsofttask.Books;

import com.example.rmsofttask.Books.dto.BookRegistrationResDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BooksServiceTest {

    @Autowired
    private BooksService booksService;

    @MockBean
    private BooksRepository booksRepository;

    private static String title;
    private static String author;


    @BeforeAll
    static void setUP(){
        title = "testTitle";
        author = "testAuthor";
    }

    @Test
    @DisplayName("책등록 테스트")
    public void testSignupSuccess() {
        when(booksRepository.save(any())).thenReturn(
                new Books(title, author, BookStatus.AVAILABLE)
        );
        BookRegistrationResDto result = booksService.registerBook(title, author);

        verify(booksRepository, times(1)).save(any(Books.class));

        assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(BookStatus.AVAILABLE.getValue(), result.getBookStatus());
    }


    @Test
    public void testUpdateBook() {
        Long bookId = 1L;
        String title = "Updated Title";
        String author = "Updated Author";

        Books mockBook = new Books(bookId, "Original Title", "Original Author", BookStatus.AVAILABLE);
        BookUpdateDto expectedDto = new BookUpdateDto(bookId, title, author);

        when(booksRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        BookUpdateDto actualDto = booksService.updateBook(bookId, title, author);

        assertNotNull(actualDto);
        assertEquals(expectedDto.getBookId(), actualDto.getBookId());
        assertEquals(expectedDto.getTitle(), actualDto.getTitle());
        assertEquals(expectedDto.getAuthor(), actualDto.getAuthor());

        verify(booksRepository, times(1)).findById(bookId);
        verifyNoMoreInteractions(booksRepository);
    }
}
