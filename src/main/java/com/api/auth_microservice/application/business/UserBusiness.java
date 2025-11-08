package com.api.auth_microservice.application.business;

import com.api.auth_microservice.application.service.UserService;
import com.api.auth_microservice.domain.entity.UserEntity;
import com.api.auth_microservice.domain.repository.UserRepository;
import com.api.auth_microservice.infrastructure.model.request.AuthUserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(AuthUserRegisterRequest request) {
        return this.userRepository.save(UserEntity.builder()
                .dni(request.getDni())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(false)
                .build());
    }

}
