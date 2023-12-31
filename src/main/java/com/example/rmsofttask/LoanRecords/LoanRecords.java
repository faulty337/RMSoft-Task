package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class LoanRecords {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books books;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate checkoutDate;

    private LocalDate returnedDate;

    @PrePersist
    public void prePersist() {
        if (checkoutDate == null) {
            checkoutDate = LocalDate.now();
        }
    }

    public LoanRecords(Books books, Users users) {
        this.books = books;
        this.users = users;
        books.updateStatus(BookStatus.CHECKED_OUT);
    }

    public LoanRecords(Books books, Users users, LocalDate checkoutDate, LocalDate returnedDate) {
        this.books = books;
        this.users = users;
        this.checkoutDate = checkoutDate;
        this.returnedDate = returnedDate;
    }

    public void updateReturnedDate(){
        this.returnedDate = LocalDate.now();
    }
}
