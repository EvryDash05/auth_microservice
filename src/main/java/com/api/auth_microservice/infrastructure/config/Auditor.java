package com.api.auth_microservice.infrastructure.config;

import com.api.auth_microservice.infrastructure.model.CustomUserDetails;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class Auditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String user = "SYSTEM";

        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            CustomUserDetails login =
                    new ModelMapper().map(
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                            CustomUserDetails.class
                    );
            if (!ObjectUtils.isEmpty(login.getUsername())) {
                user = login.getUsername();
            }
        }

        return Optional.of(user);
    }

}
