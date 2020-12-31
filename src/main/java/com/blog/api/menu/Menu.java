package com.blog.api.menu;


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
public class Menu {
    @Id
    @Column(name = "menu_id")
    private Long menuId;

    private LocalDateTime createAt;
    private long createUserId;
    private LocalDateTime updateAt;
    private long updateUserId;
}
