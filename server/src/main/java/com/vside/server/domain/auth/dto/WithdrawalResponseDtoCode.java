package com.vside.server.domain.auth.dto;

public enum WithdrawalResponseDtoCode {
    SUCCESS(0), INVALID_USERID(1),
    ;

    private final int value;
    WithdrawalResponseDtoCode(int value) { this.value = value; }
    public int getValue() { return value; }
}
