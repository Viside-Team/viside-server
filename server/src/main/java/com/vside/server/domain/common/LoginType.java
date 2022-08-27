package com.vside.server.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoginType {
    KAKAO, APPLE;

    @JsonCreator
    public static LoginType from(String s){
        return LoginType.valueOf(s.toUpperCase());
    }
}
