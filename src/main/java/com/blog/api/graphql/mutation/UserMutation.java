package com.blog.api.graphql.mutation;

import com.blog.api.user.CreateUserInput;
import com.blog.api.user.User;
import com.blog.api.user.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserMutation implements GraphQLMutationResolver {
    private final UserService userService;

    public User createUser(CreateUserInput createUserInput) {
        return userService.createUser(createUserInput);
    }
}
