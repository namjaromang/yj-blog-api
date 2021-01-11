package com.blog.api.security;

import com.blog.api.service.UserService;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        getToken(request)
                .map(userService::loadUserByToken)
                .map(
                        userDetails ->
                                JWTPreAuthenticationToken.builder()
                                        .principal(userDetails)
                                        .details(
                                                new WebAuthenticationDetailsSource()
                                                        .buildDetails(request))
                                        .build())
                .ifPresent(
                        authentication ->
                                SecurityContextHolder.getContext()
                                        .setAuthentication(authentication));
        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(Predicate.not(String::isEmpty))
                .map(BEARER_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1));
    }
}
