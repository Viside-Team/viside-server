package com.vside.server.domain.user.Entity;


import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ViUserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long userId;
    @Column
    private String userName;
}
