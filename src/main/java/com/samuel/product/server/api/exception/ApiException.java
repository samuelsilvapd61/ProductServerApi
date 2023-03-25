package com.samuel.product.server.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    protected final String code;
    protected final HttpStatus httpStatus;
    protected final String title;

    public ApiException(ApiError apiError) {
        super(apiError.getDetail());
        this.code = apiError.getCode();
        this.httpStatus = apiError.getHttpStatus();
        this.title = apiError.getTitle();
    }
}
