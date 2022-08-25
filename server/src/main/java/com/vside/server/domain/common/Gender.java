package com.vside.server.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    FEMALE, MALE;

    @JsonCreator
    public static Gender from(String s){

        System.out.println(s);
        System.out.println(Gender.valueOf(s.toUpperCase()));

        return Gender.valueOf(s.toUpperCase());
    }
}
