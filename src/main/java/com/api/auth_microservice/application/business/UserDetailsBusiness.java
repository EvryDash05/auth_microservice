package com.api.auth_microservice.application.business;

import com.api.auth_microservice.domain.entity.UserEntity;
import com.api.auth_microservice.infrastructure.model.CustomUserDetails;
import com.api.auth_microservice.infrastructure.security.utils.SecurityHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsBusiness implements UserDetailsService {

    private final SecurityHelper securityHelper;

    private static List<GrantedAuthority> createAuthorityList() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity findUser = this.securityHelper.getUserByDni(email);
        log.info("User find: {}", findUser.toString());
        return CustomUserDetails.builder()
                .userId(findUser.getId())
                .username(findUser.getDni())
                .password(findUser.getPassword())
                .authorities(createAuthorityList())
                .build();
    }

}