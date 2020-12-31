package com.blog.api.roleType;

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
public class RoleType {
    @Id
    @Column(name = "role_type_id")
    private Long roleTypeId;

    private String roleName;

    private LocalDateTime createAt;
    private long createUserId;
    private LocalDateTime updateAt;
    private long updateUserId;
}
