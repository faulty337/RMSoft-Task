package com.example.rmsofttask.Books;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {
    CHECKED_OUT("대출 중"),
    AVAILABLE("대출 가능"),
    LOST("유실");

    private final String value;
}
