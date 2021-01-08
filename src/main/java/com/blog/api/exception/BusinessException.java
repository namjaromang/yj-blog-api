package com.blog.api.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessException extends RuntimeException implements GraphQLError {
    private static final long serialVersionUID = -7098286411632701486L;

    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        extensions.put("errorMessage", errorCode.getMessage());
        extensions.put("errorCode", errorCode);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode);
    }

    public BusinessException() {
        this(ErrorCode.INVALID_INPUT_VALUE);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private Map<String, Object> extensions = new HashMap<>();

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
