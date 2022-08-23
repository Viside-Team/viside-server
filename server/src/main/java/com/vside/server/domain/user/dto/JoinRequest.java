package com.vside.server.domain.user.dto;

import com.vside.server.domain.user.Entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {
    @NotBlank @ApiModelProperty(required = true)
    private String name;

    @NotBlank @Email @ApiModelProperty(required = true)
    private String email;

    @NotBlank @ApiModelProperty(required = true, example = "kakao")
    private String loginType;

    @ApiModelProperty(example = "female")
    private String gender;

    @ApiModelProperty(example = "10~15")
    private String ageRange;

    @NotBlank @ApiModelProperty(required = true)
    private String snsId;

    public User toEntity(){
        return User.builder()
                .userName(name)
                .email(email)
                .loginType(loginType)
                .gender(gender)
                .ageRange(ageRange)
                .snsId(snsId)
                .build();
    }
}
