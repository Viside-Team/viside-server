package com.vside.server.domain.auth.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class LoginResponse {

    public LoginResponse(boolean memberStatus) {
        this.memberStatus = memberStatus;
        this.accessToken = null;
        this.refreshToken = null;
    }

    public LoginResponse(boolean memberStatus, String accessToken, String refreshToken) {
        this.memberStatus = memberStatus;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    private boolean memberStatus;

    private String accessToken;

    private String refreshToken;

    public void setAccessToken(String jwt) {
        accessToken = "Bearer " + jwt;
    }
}
