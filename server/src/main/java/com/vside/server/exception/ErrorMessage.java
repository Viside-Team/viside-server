package com.vside.server.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    INVALID_CONTENTS_REQUEST("존재하지 않는 콘텐츠 입니다."),
    INVALID_USER_REQUEST("존재하지 않는 사용자 입니다."),

    INVALID_INPUT_VALUE("잘못된 요청입니다."),
    HANDLE_ACCESS_DENIED("접근 불가"),
    INTERNAL_SERVER_ERROR("서버 에러");

    private final String message;

    ErrorMessage(final String message){
        this. message = message;
    }
}
