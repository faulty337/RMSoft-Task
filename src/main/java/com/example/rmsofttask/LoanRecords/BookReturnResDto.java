package com.example.rmsofttask.LoanRecords;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnResDto {
    private Long bookId;
    private String state;
    private LocalDate checkoutDate;
    private LocalDate returnedDate;
}
