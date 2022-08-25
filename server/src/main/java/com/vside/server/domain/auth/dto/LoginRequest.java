package com.vside.server.domain.auth.dto;

import com.vside.server.domain.common.LoginType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull @ApiModelProperty(required = true, example = "KAKAO")
    private LoginType provider;

    @NotBlank @ApiModelProperty(required = true)
    private String snsId;
}
