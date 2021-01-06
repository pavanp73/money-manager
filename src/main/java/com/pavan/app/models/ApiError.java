package com.pavan.app.models;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private final HttpStatus httpStatus;
    private final String message;
    private final int errorCode;
    private final List<String> errors;

    public ApiError(HttpStatus httpStatus, String message, int errorCode, List<String> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ApiError(HttpStatus httpStatus, String message, int errorCode, String error) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.errors = Collections.singletonList(error);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", errors=" + errors +
                '}';
    }
}
