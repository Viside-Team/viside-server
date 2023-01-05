package com.vside.server.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class nameCheckRequest {

    @NotNull
    @ApiModelProperty(required = true, example = "닉네임")
    private String name;
}
