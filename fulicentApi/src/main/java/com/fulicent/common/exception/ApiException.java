package com.fulicent.common.exception;
import com.fulicent.common.entity.ApiResponseStatus;

public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 3410820374177806832L;
    private ApiResponseStatus responseStatus;

    public ApiException(ApiResponseStatus responseStatus) {
        this(responseStatus, responseStatus.getMessage());
    }

    public ApiException(ApiResponseStatus responseStatus, String... args) {
        this(responseStatus, String.format(responseStatus.getMessage(), args));
    }

    public ApiException(ApiResponseStatus responseStatus, String errorMessage) {
        super(errorMessage);
        this.responseStatus = responseStatus;
    }

    public ApiException(ApiResponseStatus responseStatus, String errorMessage, String... args) {

        super(errorMessage);
        this.responseStatus = responseStatus;
    }

    public ApiException(ApiResponseStatus responseStatus, String error, Exception e) {
        super(error, e);
        this.responseStatus = responseStatus;
    }

    public ApiResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
