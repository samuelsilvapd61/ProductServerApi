package com.samuel.product.server.api.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
