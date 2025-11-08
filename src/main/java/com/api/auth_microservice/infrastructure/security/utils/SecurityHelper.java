package com.api.auth_microservice.infrastructure.security.utils;

import com.api.auth_microservice.domain.entity.UserEntity;
import com.api.auth_microservice.domain.repository.UserRepository;
import com.api.auth_microservice.infrastructure.exceptions.custom.NotDataFoundException;
import com.api.auth_microservice.infrastructure.model.CustomUserDetails;
import com.api.auth_microservice.infrastructure.model.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityHelper {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public List<SimpleGrantedAuthority> createAuthorityList(UserEntity user) {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    public UserEntity getUserByDni(String dni) {
        return this.userRepository.findByDni(dni)
                .orElseThrow(() -> new NotDataFoundException("Error to find user with DNI: %s".formatted(dni)));
    }

    public AuthResponse buildAuthResponse(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = createAuthorityList(user);

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .userId(user.getId())
                .authorities(authorities)
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, authorities);
        return AuthResponse.builder()
                .accessToken(jwtUtils.createToken(auth))
                .build();
    }

}
