package com.api.auth_microservice.infrastructure.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterRequest {
    private String dni;
    private String password;
}
