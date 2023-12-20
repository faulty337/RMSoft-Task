package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanRecordsService loanRecordsService;

    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDto<LoanRecordResDto>> getBookLoanRecords(@PathVariable Long bookId){
        LoanRecordResDto resDto = loanRecordsService.getLoanRecords(bookId);

        return new ResponseEntity<>(new ResponseDto<>(resDto), HttpStatus.OK);
    }
}
