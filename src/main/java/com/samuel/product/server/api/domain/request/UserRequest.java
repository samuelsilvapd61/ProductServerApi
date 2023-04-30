package com.samuel.product.server.api.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
