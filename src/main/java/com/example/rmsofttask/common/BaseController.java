package com.example.rmsofttask.common;


import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import com.example.rmsofttask.common.response.ResponseDto;
import com.example.rmsofttask.common.response.ValidResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class BaseController {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ResponseDto> handleCustomException(CustomException e){
        return new ResponseEntity<>(new ResponseDto(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<List<ValidResDto>>> handleValidationException(MethodArgumentNotValidException e) {
        List<ValidResDto> validResDtoList = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()){
            validResDtoList.add(new ValidResDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(new ResponseDto<>(ErrorCode.INVALID_PARAMETER.getMsg(), validResDtoList), HttpStatus.valueOf(400));
    }
}
