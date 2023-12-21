package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.LoanRecords.dto.LoanRecordResDto;
import com.example.rmsofttask.LoanRecords.dto.LoanRecordsDto;
import com.example.rmsofttask.Users.dto.UserIdReqDto;
import com.example.rmsofttask.common.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class LoanController {
    private final LoanRecordsService loanRecordsService;

    @GetMapping("loan/{bookId}")
    public ResponseEntity<ResponseDto<LoanRecordResDto>> getBookLoanRecords(@PathVariable Long bookId){
        LoanRecordResDto resDto = loanRecordsService.getLoanRecords(bookId);

        return new ResponseEntity<>(new ResponseDto<>(resDto), HttpStatus.OK);
    }

    @PostMapping("loan/{bookId}")
    public ResponseEntity<ResponseDto<LoanRecordsDto>> checkoutBook(@PathVariable Long bookId, @Valid @RequestBody UserIdReqDto reqDto){
        LoanRecordsDto resDto = loanRecordsService.checkoutBook(bookId, reqDto.getUserId());

        return new ResponseEntity<>(new ResponseDto<>("책의 대출 처리가 완료되었습니다.", resDto), HttpStatus.OK);
    }

    @PatchMapping("return/{bookId}")
    public ResponseEntity<ResponseDto<BookReturnResDto>> returnBook(@PathVariable Long bookId){
        BookReturnResDto resDto = loanRecordsService.returnBook(bookId);

        return new ResponseEntity<>(new ResponseDto<>("책의 반납처리가 완료되었습니다.", resDto), HttpStatus.OK);
    }
}
