package com.blog.api.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private long userRoleId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "roles", nullable = false)
    private String roles;

    @Column(name = "create_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_user_id", updatable = false, nullable = false)
    private long createUserId;

    @Column(name = "update_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_user_id", nullable = false)
    private long updateUserId;
}
