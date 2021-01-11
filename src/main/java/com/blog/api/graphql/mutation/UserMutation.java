package com.blog.api.graphql.mutation;

import com.blog.api.exception.BadCredentialsException;
import com.blog.api.model.User;
import com.blog.api.payload.request.CreateUserInput;
import com.blog.api.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserMutation implements GraphQLMutationResolver {
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;

    public User createUser(CreateUserInput createUserInput) {
        return userService.createUser(createUserInput);
    }

    @PreAuthorize("isAnonymous()")
    public User login(String loginId, String password) {
        UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(loginId, password);
        try {
            SecurityContextHolder.getContext()
                    .setAuthentication(authenticationProvider.authenticate(credentials));
            return userService.getCurrentUser();
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(loginId);
        }
    }
}
