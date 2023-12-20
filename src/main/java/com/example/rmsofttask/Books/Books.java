package com.example.rmsofttask.Books;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Books(String title, String author, BookStatus status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateAuthor(String author){
        this.author = author;
    }

    public void updateStatus(BookStatus status){
        this.status = status;
    }
}
