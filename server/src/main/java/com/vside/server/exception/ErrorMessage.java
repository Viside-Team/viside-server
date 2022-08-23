package com.vside.server.exception;

import lombok.Getter;

@Getter
enum ErrorMessage {

    INVALID_INPUT_VALUE("잘못된 요청입니다."),
    HANDLE_ACCESS_DENIED("접근 불가"),
    INTERNAL_SERVER_ERROR("서버 에러");

    private String message;

    ErrorMessage(final String message){
        this. message = message;
    }
}
