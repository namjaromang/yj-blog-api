package com.blog.api.userRole;

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
public class UserRole {
    @Id
    @Column(name = "user_role_id")
    private Long userRoleId;

    private long userId;
    private long roleTypeId;

    private LocalDateTime createAt;
    private long createUserId;
    private LocalDateTime updateAt;
    private long updateUserId;
}
