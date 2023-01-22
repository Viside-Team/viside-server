package com.vside.server.domain.scrap.Entity;


import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.user.Entity.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column
    private LocalDateTime scrapDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    public Scrap(Content content, User user){
        this.content = content;
        this.user = user;
        this.scrapDate = LocalDateTime.now();
    }
}
