package com.blog.api.graphql.resolver;

import com.blog.api.model.User;
import com.blog.api.service.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserResolver implements GraphQLResolver<User> {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    public String getToken(User user) {
        return userService.getToken(user);
    }
}
