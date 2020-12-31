package com.blog.api.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private long loginId;
    private String password;
    private String username;
    private boolean isEnable;
    private boolean withDraw;

    private LocalDateTime lastLoginDt;
    private LocalDateTime createAt;
    private long createUserId;
    private LocalDateTime updateAt;
    private long updateUserId;
}
