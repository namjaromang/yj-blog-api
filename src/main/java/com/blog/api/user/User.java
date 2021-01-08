package com.blog.api.user;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "isEnable", nullable = false)
    @Builder.Default
    private boolean isEnable = true;

    @Column(name = "withdraw", nullable = false)
    @Builder.Default
    private boolean withdraw = false;

    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private Set<String> roles;

    @Column(name = "last_login_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime lastLoginAt;

    @Column(name = "create_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_user_id", updatable = false, nullable = false)
    private long createUserId;

    @Column(name = "update_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_user_id", nullable = false)
    private long updateUserId;

    public User withRole(String role) {
        if (this.roles == null) {
            this.roles = Set.of(role);
        } else {
            this.roles.add(role);
        }
        return this;
    }

    public User withoutRole(String role) {
        if (this.roles != null) {
            this.roles.remove(role);
        }
        return this;
    }
}
