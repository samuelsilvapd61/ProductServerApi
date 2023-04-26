package com.samuel.product.server.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDto {

    private String title;
    private String detail;
    private String code;
    private Integer status;
    private LocalDateTime timestamp;
}
