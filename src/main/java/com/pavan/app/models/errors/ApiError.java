package com.pavan.app.models.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<ApiSubError> errors;

    public ApiError() {}

    public ApiError(HttpStatus status, String message, String debugMessage, List<ApiSubError> errors) {
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<ApiSubError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiSubError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                ", errors=" + errors +
                '}';
    }
}
