package com.samuel.product.server.api.exception;

import com.samuel.product.server.api.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDto> handleApiException(ApiException exception) {
        var response = buildApiError(exception);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    private ApiErrorDto buildApiError(ApiException e) {
        return buildApiError(e.getMessage(), e.getCode(), e.getTitle(), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var e = exception.getFieldError().getCodes()[0];
        var enumEncontrado = ApiError.encontrarEnum(e);

        return handleApiException(new ApiException(Objects.requireNonNullElse(enumEncontrado, ApiError.UNKNOW_VALIDATION_ERROR)));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        if (exception.getCause().getCause() == null) {
            var mensagem = "Erro de formatação desconhecido";
            var titulo = "Erro de formatação";
            var statusCode = HttpStatus.BAD_REQUEST;
            var response = buildApiError(mensagem, "NR-4000", titulo, statusCode);
            return ResponseEntity.status(response.getStatus()).body(response);

        } else {
            var mensagem = exception.getCause().getCause().getMessage();
            var titulo = "Erro de formatação";
            var statusCode = HttpStatus.BAD_REQUEST;
            var response = buildApiError(mensagem, "NR-4000", titulo, statusCode);
            return ResponseEntity.status(response.getStatus()).body(response);
        }

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {

        var mensagem = "Algum parâmetro foi não informado corretamente";
        if (exception.getCause() != null) {
            mensagem = exception.getCause().getMessage();
            if(exception.getCause().getCause() != null) {
                mensagem = exception.getCause().getCause().getMessage();
            }
        }
        var titulo = "Erro de formatação - parâmetro inválido";
        var statusCode = HttpStatus.BAD_REQUEST;
        var response = buildApiError(mensagem, "TM-4000", titulo, statusCode);

        return ResponseEntity.status(response.getStatus()).body(response);
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

    private ApiErrorDto buildApiError(ApiError e) {
        return buildApiError(e.getFormattedMessage(), e.getCode(), e.getTitle(), e.getHttpStatus());
    }
}
