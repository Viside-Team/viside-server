package com.vside.server.domain.user.Entity;

import com.vside.server.domain.common.Gender;
import com.vside.server.domain.common.LoginType;
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

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(name = "login_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age_range")
    private String ageRange;

    @Column(name = "sns_id", nullable = false, unique = true)
    private String snsId;

    @Builder
    public User(Long userId, String userName, String email, LoginType loginType, LocalDateTime joinDate, Gender gender, String ageRange, String snsId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.loginType = loginType;
        this.joinDate = defaultIfNull(joinDate, now());
        this.gender = gender;
        this.ageRange = ageRange;
        this.snsId = snsId;
    }
}
