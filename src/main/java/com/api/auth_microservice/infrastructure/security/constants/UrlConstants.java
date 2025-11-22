package com.api.auth_microservice.infrastructure.security.constants;

public class UrlConstants {

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/auth/register",
            "/auth/login"
    };

    public static final String[] AUTH_URLS = {
            "/auth/send-email-recovery-password",
            "/auth/reset-password",
            "/auth/refresh-token",
            "/auth/test"
    };

    public static final String[] DEV_URLS = {
            "http://localhost:5173",
            "http://localhost:3000",
            "https://fronbilletera-digital-production.up.railway.app"
    };

    private UrlConstants() {
    }
}
