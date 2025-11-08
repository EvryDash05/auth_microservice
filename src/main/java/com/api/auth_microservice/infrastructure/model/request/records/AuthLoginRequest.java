package com.api.auth_microservice.infrastructure.model.request.records;

public record AuthLoginRequest(String email, String password) {
}
