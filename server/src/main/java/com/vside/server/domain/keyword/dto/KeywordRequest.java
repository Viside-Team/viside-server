package com.vside.server.domain.keyword.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeywordRequest {
    @NotBlank
    @ApiModelProperty(required = true)
    private List keywordList;

}
