package com.example.rmsofttask.LoanRecords;


import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Books.BooksRepository;
import com.example.rmsofttask.Users.Users;
import com.example.rmsofttask.Users.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanRecordsCoincidenceTest {
    @Autowired
    private LoanRecordsService loanRecordsService;

    @Autowired
    private LoanRecordsRepository loanRecordsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Test
    @DisplayName("동시성 확인")
    public void testCheckOutCoincidence(){
        Books book = new Books("어린왕자", "생텍쥐페리", BookStatus.AVAILABLE);
        Users user1 = new Users("faulty337", "김범준", "faulty337@gmail.com", "010-0000-0000", "qwer1234");
        Users user2 = new Users("faulty331", "김밤준", "faulty331@gmail.com", "010-1111-1111", "qwer1233");

        usersRepository.save(user1);
        usersRepository.save(user2);
        booksRepository.save(book);
        CompletableFuture.allOf(
                CompletableFuture.runAsync(()->loanRecordsService.checkoutBook(book.getId(), user1.getUserId())),
                CompletableFuture.runAsync(()->loanRecordsService.checkoutBook(book.getId(), user2.getUserId())),
                CompletableFuture.runAsync(()->loanRecordsService.checkoutBook(book.getId(), user1.getUserId()))
        ).join();
        assertEquals(1,loanRecordsRepository.findAllByBooks(book).size());
    }
}
