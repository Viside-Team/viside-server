package com.vside.server.domain.content.Entity;

import com.vside.server.domain.keyword.Entity.Keyword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ContentKeyword {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_title")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "keyword")
    private Keyword keyword;


}
