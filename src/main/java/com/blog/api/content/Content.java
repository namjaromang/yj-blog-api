package com.blog.api.content;

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
public class Content {
    @Id
    @Column(name = "context_id")
    private Long contentId;

    @Column(name = "menu_id" , nullable = false)
    private long menuId;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "create_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createAt;
    @Column(name = "create_user_id", updatable = false, nullable = false)
    private long createUserId;
    @Column(name = "update_dt", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updateAt;
    @Column(name = "update_user_id", nullable = false)
    private long updateUserId;
}
