package com.vside.server.domain.auth.dto;

import lombok.*;

@Getter
public class LoginResponse {

    public LoginResponse(boolean memberStatus) {
        this.memberStatus = memberStatus;
        this.accessToken = null;
        this.refreshToken = null;
    }

    public LoginResponse(boolean memberStatus, String accessToken, String refreshToken) {
        this.memberStatus = memberStatus;
        this.accessToken = "Bearer " + accessToken;
        this.refreshToken = refreshToken;
    }

    private final boolean memberStatus;

    private final String accessToken;

    private final String refreshToken;
}
