package com.example.rmsofttask.Books.dto;

import com.example.rmsofttask.Books.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRegistrationResDto {

    private Long bookId;
    private String title;
    private String author;
    private BookStatus bookStatus;


    public String getBookStatus(){
        return this.bookStatus.getValue();
    }
}
