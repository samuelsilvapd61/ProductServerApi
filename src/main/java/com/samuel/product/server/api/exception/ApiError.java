package com.samuel.product.server.api.exception;


import com.samuel.product.server.api.util.Messages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.samuel.product.server.api.exception.ApiError.Constants.*;

@Getter
public enum ApiError {

    PRODUCT_NOT_FOUND                       ("PV-4040", "msg.product.not-found", HttpStatus.NOT_FOUND, TITLE_VALIDATION_ERROR),
    ID_NOT_BLANK                            ("PV-4000", "msg.product.id.not-blank", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    PRODUCT_NAME_NOT_BLANK                  ("PV-4001", "NotBlank.productRequest.name", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    PRODUCT_CATEGORY_NOT_BLANK              ("PV-4002", "NotBlank.productRequest.category", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    PRODUCT_QUANTITY_NOT_NULL               ("PV-4003", "NotNull.productRequest.quantity", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    PRODUCT_QUANTITY_NOT_NEGATIVE           ("PV-4004", "Min.productRequest.quantity", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    UNKNOW_VALIDATION_ERROR                 ("PV-4005", "msg.unknow-validation-error", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    USER_EMAIL_NOT_NULL                     ("UV-4000", "NotBlank.userRequest.email", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    USER_PASSWORD_NOT_NULL                  ("UV-4001", "NotBlank.userRequest.password", HttpStatus.BAD_REQUEST, TITLE_VALIDATION_ERROR),
    PRODUCT_ID_TYPE_MISMATCH                ("TM-4000", "TypeMismatch.productRequest.id", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_NAME_TYPE_MISMATCH              ("TM-4001", "TypeMismatch.productRequest.name", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_DESCRIPTION_TYPE_MISMATCH       ("TM-4002", "TypeMismatch.productRequest.description", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_CATEGORY_TYPE_MISMATCH          ("TM-4003", "TypeMismatch.productRequest.category", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_BRAND_TYPE_MISMATCH             ("TM-4004", "TypeMismatch.productRequest.productBrand", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_PROVIDER_TYPE_MISMATCH          ("TM-4005", "TypeMismatch.productRequest.provider", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_QUANTITY_TYPE_MISMATCH          ("TM-4006", "TypeMismatch.productRequest.quantity", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_BAR_CODE_TYPE_MISMATCH          ("TM-4007", "TypeMismatch.productRequest.barCode", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_FABRICATION_DATE_TYPE_MISMATCH  ("TM-4008", "TypeMismatch.productRequest.fabricationDate", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_EXPIRATION_DATE_TYPE_MISMATCH   ("TM-4009", "TypeMismatch.productRequest.expirationDate", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    PRODUCT_INCLUSION_DATE_TYPE_MISMATCH    ("TM-40010", "TypeMismatch.productRequest.inclusionDate", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    UNKNOW_TYPE_MISMATCH_ERROR              ("TM-40011", "msg.unknow-type-tismatch-error", HttpStatus.BAD_REQUEST, TITLE_TYPE_MISMATCH_ERROR),
    UNKNOW_ERROR                            ("UE-5000", "msg.unknow-error", HttpStatus.INTERNAL_SERVER_ERROR, TITLE_UNKNOW_SERVER_ERROR),
    INCORRECT_USER_OR_PASSWORD              ("AD-4010", "msg.incorrect-username-or-password", HttpStatus.UNAUTHORIZED, TITLE_ACCESS_DENIED),
//    TOKEN_EXPIRED                           ("AD-4011", "msg.token-expired", HttpStatus.UNAUTHORIZED, TITLE_ACCESS_DENIED),
    HTTP_NOT_READEBLE                       ("NR-4000", "msg.http-not-readeble", HttpStatus.BAD_REQUEST, TITLE_HTTP_NOT_READABLE);


    private String code;

    private String messageKey;
    private HttpStatus httpStatus;
    private String title;
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

    public static ApiError encontrarEnum(String valor) {
        for (ApiError e : ApiError.values()) {
            if (e.messageKey.equalsIgnoreCase(valor)) {
                return e;
            }
        }
        return null;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Constants {
        static final String TITLE_VALIDATION_ERROR = "Erro de validação";
        static final String TITLE_TYPE_MISMATCH_ERROR = "Erro de formatação";
        static final String TITLE_UNKNOW_SERVER_ERROR = "Unknow Server Error";
        static final String TITLE_ACCESS_DENIED = "Access Denied";
        static final String TITLE_HTTP_NOT_READABLE = "Not Readable";
    }
}
