package com.api.auth_microservice.commons.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER("USER");

    private final String code;

    UserRoles(String code) {
        this.code = code;
    }

}
