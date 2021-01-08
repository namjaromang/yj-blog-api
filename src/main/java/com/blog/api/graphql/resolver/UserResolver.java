package com.blog.api.graphql.resolver;

import com.blog.api.user.User;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserResolver implements GraphQLResolver<User> {}
