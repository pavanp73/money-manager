package com.pavan.app.exceptions;

import com.pavan.app.models.errors.ApiError;
import com.pavan.app.models.errors.ApiSubError;
import com.pavan.app.models.errors.ApiValidationError;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        List<ApiSubError> errors = ex.getFieldErrors().stream()
                .map(fieldError -> new ApiValidationError(
                        fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return handleException(HttpStatus.BAD_REQUEST, "Validation Failure", ex.getLocalizedMessage(), errors);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
      return handleException(HttpStatus.NOT_FOUND, ex.getMessage(), "", null);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String errorMessage = ex.getCause().getMessage();
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ex.getLocalizedMessage(), null);
    }

    @ExceptionHandler({TransferAccountCannotBeEmptyException.class})
    public ResponseEntity<Object> handleTransferAccountCannotBeEmptyException(TransferAccountCannotBeEmptyException ex, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, "Validation Failure", ex.getMessage(), null);
    }

    private ResponseEntity<Object> handleException(HttpStatus status, String message, String debugMessage, List<ApiSubError> errors) {
        return new ResponseEntity<>(
                new ApiError(status, message, debugMessage, errors),
                new HttpHeaders(),
                status);
    }
}
