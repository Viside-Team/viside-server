package com.vside.server.domain.content.Entity;

import com.vside.server.domain.keyword.Entity.Keyword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ContentKeyword {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_title")
    private Content content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword")
    private Keyword keyword;


}
