package com.example.rmsofttask.common;


import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseController {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ResponseDto> handleCustomException(CustomException e){
        return new ResponseEntity<>(new ResponseDto(e.getErrorCode()), HttpStatus.OK);
    }
}
