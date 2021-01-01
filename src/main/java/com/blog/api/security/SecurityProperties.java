package com.blog.api.security;

import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "api.security")
@Getter
@RequiredArgsConstructor
public class SecurityProperties {
    /** Amound of hashing iterations, where formula is 2^passwordStrength iterations */
    private final int passwordStrength = 10;
    /** Secret used to generate and verify JWT tokens */
    private final String tokenSecret = "NfECoU9_)icgPDx";
    /** Name of the token issuer */
    private final String tokenIssuer = "blogApi";
    /** Duration after which a token will expire */
    private final Duration tokenExpiration = Duration.ofHours(4);
}
