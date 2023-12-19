package com.example.rmsofttask.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_PARAMETER("파라미터 값을 확인해주세요.", 400),
    DUPLICATE_USERID("유저ID가 중복되었습니다.", 400),
    NOT_FOND_BOOK_ID("해당 책ID를 찾을 수 없습니다.", 400);

    private final String msg;
    private final int statusCode;
}
