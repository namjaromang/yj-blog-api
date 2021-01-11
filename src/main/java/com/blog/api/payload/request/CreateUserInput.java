package com.blog.api.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
public class CreateUserInput {
    private String loginId;
    private String password;
    private String username;
}
