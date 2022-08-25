package com.vside.server.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    @Setter
    private boolean memberStatus;
    private String jwt;

    public void setJwt(String jwt) {
        this.jwt = "Bearer " + jwt;
    }
}
