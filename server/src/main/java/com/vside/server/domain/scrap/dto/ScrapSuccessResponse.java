package com.vside.server.domain.scrap.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapSuccessResponse {
    private boolean status;
    private String message;
}
