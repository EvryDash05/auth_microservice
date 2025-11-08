package com.api.auth_microservice.application.service;


import com.api.auth_microservice.domain.entity.UserEntity;
import com.api.auth_microservice.infrastructure.model.request.AuthUserRegisterRequest;
import com.api.auth_microservice.infrastructure.model.response.UserDetailsResponse;

public interface UserService {
    UserEntity registerUser(AuthUserRegisterRequest request);
}
