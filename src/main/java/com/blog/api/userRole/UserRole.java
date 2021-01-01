package com.blog.api.userRole;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {
    @Id
    @Column(name = "user_role_id")
    private long userRoleId;

    @Column(name = "user_id" , nullable = false)
    private long userId;

    @Column(name = "role_type_id" , nullable = false)
    private long roleTypeId;

    @Column(name = "create_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createAt;
    @Column(name = "create_user_id", updatable = false, nullable = false)
    private long createUserId;
    @Column(name = "update_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updateAt;
    @Column(name = "update_user_id", nullable = false)
    private long updateUserId;
}
