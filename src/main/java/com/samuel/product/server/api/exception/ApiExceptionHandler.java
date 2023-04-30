package com.samuel.product.server.api.exception;

import com.samuel.product.server.api.util.DateUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDto> handleApiException(ApiException exception) {
        var response = buildApiError(exception);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var e = exception.getFieldError().getCodes()[0];
        var enumEncontrado = ApiError.encontrarEnum(e);
        return handleApiException(new ApiException(Objects.requireNonNullElse(enumEncontrado, ApiError.UNKNOW_VALIDATION_ERROR)));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return handleApiException(new ApiException(ApiError.HTTP_NOT_READEBLE));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {

        var fieldError = exception.getParameter().getParameterName();
        var messageKey = "TypeMismatch.productRequest."+fieldError;

        var enumEncontrado = ApiError.encontrarEnum(messageKey);
        return handleApiException(new ApiException(Objects.requireNonNullElse(enumEncontrado, ApiError.UNKNOW_TYPE_MISMATCH_ERROR)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> exception(Exception exception) {
        return handleApiException(new ApiException(ApiError.UNKNOW_ERROR));
    }

    private ApiErrorDto buildApiError(String message, String code, String title, HttpStatus httpStatus) {
        return ApiErrorDto.builder()
                .title(title)
                .detail(message)
                .status(httpStatus.value())
                .code(code)
                .timestamp(DateUtil.nowDateTime())
                .build();
    }

    private ApiErrorDto buildApiError(ApiException e) {
        return buildApiError(e.getMessage(), e.getCode(), e.getTitle(), e.getHttpStatus());
    }
}
