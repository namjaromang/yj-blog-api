package com.blog.api.repository;

import com.blog.api.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginIdAndWithdrawFalseAndEnableIsTrue(String loginId);
}
