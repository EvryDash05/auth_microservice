package com.api.auth_microservice.application.service;

import com.api.auth_microservice.infrastructure.model.request.records.AuthLoginRequest;
import com.api.auth_microservice.infrastructure.model.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthLoginRequest request);
}
