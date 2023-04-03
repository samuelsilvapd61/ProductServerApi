package com.samuel.product.server.api.exception;


import com.samuel.product.server.api.util.Messages;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static com.samuel.product.server.api.exception.ApiError.Constants.TITLE_VALIDATION_ERROR;

@Getter
public enum ApiError {

    PRODUCT_NOT_FOUND("PV-4000", "msg.product.not-found", HttpStatus.NOT_FOUND, TITLE_VALIDATION_ERROR),
    ID_NOT_BLANK("PV-4001", "ID de produto não pode ser vazio", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR);

    private String code;
    private String detail;
    private HttpStatus httpStatus;
    private String title;

    private String messageKey;
    private Object[] messageParams;

    private ApiError(String code, String messageKey, HttpStatus status) {
        this(code, messageKey, status, null);
    }

    private ApiError(String code, String messageKey, HttpStatus status, String title) {
        this.code = code;
        this.messageKey = messageKey;
        this.httpStatus = status;
        this.title = resolveTitle(status, title);
    }

    private String resolveTitle(HttpStatus status, String title) {
        return title == null ?
                status.getReasonPhrase() :
                Messages.get(title);
    }

    public String getFormattedMessage() {
        return Messages.get(this.messageKey, this.messageParams);
    }

    public ApiError formatMessage(Object... params) {
        this.messageParams = params;
        return this;
    }


    /*public static ApiError fromMessageCode(String messageCode) {
        return Arrays.stream(values())
                .filter(code -> code.getDetail().equals(messageCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid message code: " + messageCode));
    }*/



    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Constants {
        static final String TITLE_VALIDATION_ERROR = "Erro de validação";
    }
}
