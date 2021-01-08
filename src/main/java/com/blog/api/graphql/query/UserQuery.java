package com.blog.api.graphql.query;

import com.blog.api.user.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserQuery implements GraphQLQueryResolver {
    private final UserService userService;

    public void userinfo(String loginId) {
        userService.loadUserByUsername(loginId);
    }
}
