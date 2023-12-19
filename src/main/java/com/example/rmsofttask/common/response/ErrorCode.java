package com.example.rmsofttask.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_PARAMETER("파라미터 값을 확인해주세요.", 400);

    private final String msg;
    private final int statusCode;
}