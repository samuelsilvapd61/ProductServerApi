package com.samuel.product.server.api.exception;

import com.samuel.product.server.api.util.Messages;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
public class ApiException extends RuntimeException {

    public static final String PREFIX_MSG_KEY = "msg.";
    protected String code;
    protected HttpStatus httpStatus;
    protected String title;


    public ApiException(ApiError apiError) {
        super(apiError.getFormattedMessage());
        fillFields(apiError.getHttpStatus(), apiError.getCode(), apiError.getTitle());
    }

    public ApiException(ApiError apiError, Throwable t) {
        super(apiError.getFormattedMessage(), t);
        fillFields(apiError.getHttpStatus(), apiError.getCode(), apiError.getTitle());
    }

    protected void fillFields(HttpStatus httpStatus, String errorCode, String title) {
        this.httpStatus = Optional.ofNullable(httpStatus)
                .orElseThrow(() -> new IllegalArgumentException("http status cannot be null"));
        this.code = Optional.ofNullable(errorCode)
                .orElseThrow(() -> new IllegalArgumentException("error code cannot be null"));
        this.title = StringUtils.isBlank(title) ? httpStatus.getReasonPhrase() : title.trim();
    }

    public static String handleExceptionMessage(String value, Object... params) {
        return value.startsWith(PREFIX_MSG_KEY) ? Messages.get(value, params) : value;
    }
}
