package com.vside.server.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull @ApiModelProperty(required = true, example = "kakao")
    private String provider;
    @NotNull @ApiModelProperty(required = true)
    private String snsId;
}
