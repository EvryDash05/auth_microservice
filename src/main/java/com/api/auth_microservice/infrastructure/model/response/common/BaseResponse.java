package com.api.auth_microservice.infrastructure.model.response.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private Integer status;
    private T data;
    private List<?> errors;
}
