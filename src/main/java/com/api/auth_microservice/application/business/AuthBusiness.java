package com.api.auth_microservice.application.business;

import com.api.auth_microservice.application.service.AuthService;
import com.api.auth_microservice.application.service.UserService;
import com.api.auth_microservice.domain.entity.UserEntity;
import com.api.auth_microservice.domain.repository.UserRepository;
import com.api.auth_microservice.infrastructure.exceptions.custom.NotDataFoundException;
import com.api.auth_microservice.infrastructure.model.request.AuthUserRegisterRequest;
import com.api.auth_microservice.infrastructure.model.request.records.AuthLoginRequest;
import com.api.auth_microservice.infrastructure.model.response.AuthResponse;
import com.api.auth_microservice.infrastructure.security.utils.SecurityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthBusiness implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SecurityHelper securityHelper;
    private final UserDetailsBusiness userDetailService;
    private final UserService userService;

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        Authentication authentication = this.authenticate(request.dni(), request.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity findUser = this.getUserByDni(request.dni());

        return this.securityHelper.buildAuthResponse(findUser);
    }

    private Authentication authenticate(String dni, String password) {
        UserDetails userDetails = this.userDetailService.loadUserByUsername(dni);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Transactional
    public AuthResponse registerUser(AuthUserRegisterRequest request) {

        UserEntity newUser = this.userRepository.save(UserEntity.builder()
                .dni(request.getDni())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(false)
                .build());

        Authentication authentication = this.authenticate(request.getDni(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return this.securityHelper.buildAuthResponse(newUser);
    }

    private UserEntity getUserByDni(String email) {
        return this.userRepository.findByDni(email)
                .orElseThrow(() -> new NotDataFoundException("User not found"));
    }

    private void createUserAndRoles(AuthUserRegisterRequest request) {
        UserEntity newUser = this.userService.registerUser(request);
    }

}
