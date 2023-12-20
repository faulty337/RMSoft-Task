package com.example.rmsofttask.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_PARAMETER("파라미터 값을 확인해주세요.", 400),
    DUPLICATE_USERID("유저ID가 중복되었습니다.", 400),
    NOT_FOUND_BOOK_ID("해당 책ID를 찾을 수 없습니다.", 400),
    NOT_FOUND_USER_ID("해당 유저ID를 찾을 수 없습니다.", 400),
    BOOK_LOAN_LIMIT_EXCEEDED("최대 대출 가능 권 수를 넘겼습니다.", 400),
    BOOK_NOT_AVAILABLE("책이 현재 대출가능한 상태가 아닙니다.", 400),
    MAX_CHECKOUT_EXCEEDED("현재 대출 가능한 권수를 초과했습니다.", 400),
    OVERDUE_BOOKS_EXIST("연체된 책이 존재하여 책을 대출할 수 없습니다.", 400);

    private final String msg;
    private final int statusCode;
}
