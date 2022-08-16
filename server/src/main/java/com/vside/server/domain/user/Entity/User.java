package com.vside.server.domain.user.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Entity
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"userId", "userName"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    private String email;

    @Column(name = "login_type")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age_range")
    private String ageRange;

    @Builder
    public User(Long userId, String userName, String email, LoginType loginType, LocalDateTime joinDate, Gender gender, String ageRange) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.loginType = loginType;
        this.joinDate = defaultIfNull(joinDate, now());
        this.gender = gender;
        this.ageRange = ageRange;
    }
}
