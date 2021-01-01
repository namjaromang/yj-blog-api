package com.blog.api.roleType;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleType {
    @Id
    @Column(name = "role_type_id")
    private Long roleTypeId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "create_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_user_id", updatable = false, nullable = false)
    private long createUserId;

    @Column(name = "update_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_user_id", nullable = false)
    private long updateUserId;
}
