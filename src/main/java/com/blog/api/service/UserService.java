package com.blog.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blog.api.constant.Const;
import com.blog.api.exception.BadTokenException;
import com.blog.api.model.User;
import com.blog.api.model.UserRole;
import com.blog.api.payload.request.CreateUserInput;
import com.blog.api.repository.UserRepository;
import com.blog.api.repository.UserRoleRepository;
import com.blog.api.security.JWTUserDetails;
import com.blog.api.security.SecurityProperties;
import com.blog.api.utility.StreamUtils;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final SecurityProperties securityProperties;
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public JWTUserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return userRepository
                .findByLoginId(loginId)
                .map(user -> getUserDetails(user, getToken(user)))
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username or password didn''t match"));
    }

    @Transactional
    public String getToken(User user) {
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(securityProperties.getTokenExpiration());
        return JWT.create()
                .withIssuer(securityProperties.getTokenIssuer())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .withSubject(user.getLoginId())
                .sign(algorithm);
    }

    @Transactional
    public JWTUserDetails loadUserByToken(String token) {
        return getDecodedToken(token)
                .map(DecodedJWT::getSubject)
                .flatMap(userRepository::findByLoginIdAndWithdrawFalseAndEnableIsTrue)
                .map(user -> getUserDetails(user, token))
                .orElseThrow(BadTokenException::new);
    }

    private JWTUserDetails getUserDetails(User user, String token) {
        return JWTUserDetails.builder()
                .username(user.getLoginId())
                .password(user.getPassword())
                .authorities(
                        StreamUtils.collectionStream(user.getRoles())
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()))
                .token(token)
                .build();
    }

    @Transactional
    public User getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findByLoginId)
                .orElse(null);
    }

    public boolean isAuthenticated() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(Predicate.not(this::isAnonymous))
                .isPresent();
    }

    private Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(jwtVerifier.verify(token));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }

    @Transactional
    public User createUser(CreateUserInput input) {
        User user =
                userRepository.save(
                        User.builder()
                                .loginId(input.getLoginId())
                                .password(passwordEncoder.encode(input.getPassword()))
                                .username(input.getUsername())
                                .build());

        user.setCreateUserId(user.getUserId());
        user.setUpdateUserId(user.getUserId());

        userRoleRepository.save(
                UserRole.builder()
                        .userId(user.getUserId())
                        .roles(Const.ROLE_USER)
                        .createUserId(user.getUserId())
                        .updateUserId(user.getUserId())
                        .build());

        return userRepository.save(user);
    }
}
