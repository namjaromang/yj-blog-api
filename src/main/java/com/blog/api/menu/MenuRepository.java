package com.blog.api.menu;

import com.blog.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
