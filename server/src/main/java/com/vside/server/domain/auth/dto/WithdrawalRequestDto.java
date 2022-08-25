package com.vside.server.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalRequestDto {

    @NotBlank
    @ApiModelProperty(required = true)
    private String snsId;
}
