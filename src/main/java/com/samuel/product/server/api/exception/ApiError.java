package com.samuel.product.server.api.exception;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static com.samuel.product.server.api.exception.ApiError.Constants.TITLE_VALIDATION_ERROR;

@Getter
@AllArgsConstructor
public enum ApiError {

    PRODUCT_NOT_FOUND("PV-4000", "ID de produto não encontrado", HttpStatus.NOT_FOUND, TITLE_VALIDATION_ERROR),
    ID_NOT_BLANK("PV-4001", "ID de produto não pode ser vazio", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR);

    private final String code;
    private final String detail;
    private final HttpStatus httpStatus;
    private final String title;


    public static ApiError fromMessageCode(String messageCode) {
        return Arrays.stream(values())
                .filter(code -> code.getDetail().equals(messageCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid message code: " + messageCode));
    }


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Constants {
        static final String TITLE_VALIDATION_ERROR = "Erro de validação";
    }
}
