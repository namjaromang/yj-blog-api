package com.blog.api.security;

import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "api.security")
@Getter
@RequiredArgsConstructor
public class SecurityProperties {
    /** Amound of hashing iterations, where formula is 2^passwordStrength iterations */
    private final int passwordStrength = 11;
    /** Secret used to generate and verify JWT tokens */
    @Value("${blog.tokenSecret}")
    private final String tokenSecret;
    /** Name of the token issuer */
    @Value("${blog.tokenIssuer}")
    private final String tokenIssuer;
    /** Duration after which a token will expire */
    private final Duration tokenExpiration = Duration.ofHours(4);
}
