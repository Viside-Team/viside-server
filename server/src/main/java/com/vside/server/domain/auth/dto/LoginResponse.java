package com.vside.server.domain.auth.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @Setter
    private boolean memberStatus;
    private String jwt;

    public void setJwt(String jwt) {
        this.jwt = "Bearer " + jwt;
    }
}
