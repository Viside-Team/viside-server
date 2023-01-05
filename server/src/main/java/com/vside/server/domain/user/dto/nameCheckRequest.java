package com.vside.server.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class nameCheckRequest {

    @NotNull
    @ApiModelProperty(required = true, example = "닉네")
    String name;
}
