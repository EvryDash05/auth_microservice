package com.api.auth_microservice.infrastructure.api;

import com.api.auth_microservice.application.business.AuthBusiness;
import com.api.auth_microservice.commons.constants.ApiConstants;
import com.api.auth_microservice.infrastructure.model.request.AuthUserRegisterRequest;
import com.api.auth_microservice.infrastructure.model.request.records.AuthLoginRequest;
import com.api.auth_microservice.infrastructure.model.response.AuthResponse;
import com.api.auth_microservice.infrastructure.model.response.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiConstants.AUTH_PREFIX_ENDPOINT)
public class AuthController {

    private final AuthBusiness authBusiness;

    @PostMapping(ApiConstants.REGISTER_ENDPOINT)
    public ResponseEntity<BaseResponse<?>> register(@RequestBody AuthUserRegisterRequest request) {
        AuthResponse authResponse =  this.authBusiness.registerUser(request);
        return ResponseEntity.ok(BaseResponse.<AuthResponse>builder()
                .message("Register successfully")
                .status(201)
                .data(authResponse)
                .build()
        );
    }

    @PostMapping(ApiConstants.LOGIN_ENDPOINT)
    public ResponseEntity<BaseResponse<AuthResponse>> login(@RequestBody AuthLoginRequest request) {
        AuthResponse authResponse = this.authBusiness.login(request);
        return ResponseEntity.ok(BaseResponse.<AuthResponse>builder()
                .message("Login successfully")
                .status(200)
                .data(authResponse)
                .build()
        );
    }

//    @PostMapping(ApiConstants.REFRESH_TOKEN_ENDPOINT)
//    public ResponseEntity<AuthResponse> refreshToken() {
//        return ResponseEntity.ok(this.refreshTokenBusiness.createNewAccessToken());
//    }

//    @GetMapping("/find-profile")
//    public ResponseEntity<UserDetailsResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
//        return ResponseEntity.ok(this.userBusiness.findUserData(userDetails.getUserId()));
//    }

//    @PostMapping(ApiConstants.SEND_EMAIL_PASSWORD_RECOVERY_ENDPOINT)
//    public ResponseEntity<String> recoveryPasswordRequest(@RequestBody SendRequestPasswordRequest request) {
//        return ResponseEntity.ok(this.recoveryPasswordService.sendRequestRecoverPassword(request).token);
//    }

//    @PatchMapping(ApiConstants.RESET_PASSWORD_ENDPOINT)
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request) {
//        Map<String, String> response = new HashMap<>();
//        this.recoveryPasswordService.updatePassword(request, token);
//        return ResponseEntity.ok(response.put("message", "Password updated successfully"));
//    }

    @PostMapping(ApiConstants.TEST_ENDPOINT)
    public ResponseEntity<String> testing() {
        return ResponseEntity.ok("Status, OK!");
    }

}
