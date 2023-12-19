package com.example.rmsofttask.Books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDto {
    private Long bookId;
    private String title;
    private String author;
}
