package com.api.auth_microservice.infrastructure.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private String userDetailId;
    private String districtName;
    private String email;
    private String lastName;
}
