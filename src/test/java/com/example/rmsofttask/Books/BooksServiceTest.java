package com.example.rmsofttask.Books;

import com.example.rmsofttask.Books.dto.BookRegistrationResDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
}
