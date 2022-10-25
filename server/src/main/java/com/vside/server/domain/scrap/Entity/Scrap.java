package com.vside.server.domain.scrap.Entity;


import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.user.Entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@Getter
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long scrapId;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Scrap(Content content, User user){
        this.content = content;
        this.user = user;
    }
}
