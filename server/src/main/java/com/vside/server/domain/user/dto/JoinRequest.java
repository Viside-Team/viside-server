package com.vside.server.domain.user.dto;

import com.vside.server.domain.common.Gender;
import com.vside.server.domain.common.LoginType;
import com.vside.server.domain.user.Entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {
    @NotBlank @ApiModelProperty(required = true)
    private String name;

    @NotBlank @Email @ApiModelProperty(required = true, example = "xxx@naver.com")
    private String email;

    @NotNull @ApiModelProperty(required = true, example = "KAKAO")
    private LoginType loginType;

    @ApiModelProperty(example = "FEMALE")
    private Gender gender;

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
