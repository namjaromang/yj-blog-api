package com.blog.api.exception;

import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 8369435603356630425L;
    private final String loginId;

    @Override
    public String getMessage() {
        return MessageFormat.format("A user already exists with email ''{0}''", loginId);
    }
}
