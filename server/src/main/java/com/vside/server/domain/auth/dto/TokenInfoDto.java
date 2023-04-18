package com.vside.server.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfoDto {

    private String userId;
    private String accessToken;
    private String refreshToken;
}
