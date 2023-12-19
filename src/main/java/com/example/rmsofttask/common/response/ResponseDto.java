package com.example.rmsofttask.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ResponseDto<T> {

    private String message;
    private Integer StatusCode;
    private T data;

    public ResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.StatusCode = statusCode;
    }

    public ResponseDto(ErrorCode errorCode){
        this.message = errorCode.getMsg();
        this.StatusCode = errorCode.getStatusCode();
    }
}
