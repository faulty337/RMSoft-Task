package com.example.rmsofttask.LoanRecords.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRecordResDto {
    private Long BookId;
    private String title;
    private String author;
    private List<LoanRecordsDto> loanRecordsDtoList;
}
