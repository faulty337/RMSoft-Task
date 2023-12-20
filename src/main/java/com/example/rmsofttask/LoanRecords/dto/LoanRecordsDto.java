package com.example.rmsofttask.LoanRecords.dto;

import com.example.rmsofttask.LoanRecords.LoanRecords;
import com.example.rmsofttask.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRecordsDto {
    private String userId;
    private LocalDate checkoutDate;
    private LocalDate returnedDate;

    public LoanRecordsDto(LoanRecords loanRecords){
        Users user = loanRecords.getUsers();
        this.userId = user.getUserId();
        this.checkoutDate = loanRecords.getCheckoutDate();
        this.returnedDate = loanRecords.getReturnedDate();
    }
}
