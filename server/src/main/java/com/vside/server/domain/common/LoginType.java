package com.vside.server.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoginType {
    KAKAO, APPLE;

    @JsonCreator
    public static LoginType from(String s){
        System.out.println(s);
        System.out.println(LoginType.valueOf(s.toUpperCase()));
        return LoginType.valueOf(s.toUpperCase());
    }
}
