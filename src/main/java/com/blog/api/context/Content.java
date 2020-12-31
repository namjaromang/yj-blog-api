package com.blog.api.context;

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
public class Content {
    @Id
    @Column(name = "context_id")
    private Long contentId;
    private long menuId;
    private String title;
    private String content;

    private LocalDateTime createAt;
    private long createUserId;
    private LocalDateTime updateAt;
    private long updateUserId;
}
