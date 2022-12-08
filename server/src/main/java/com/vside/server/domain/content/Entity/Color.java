package com.vside.server.domain.content.Entity;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Color {
    private String lighterColor;
    private String darkerColor;
}
