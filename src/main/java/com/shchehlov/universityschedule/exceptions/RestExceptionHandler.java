package com.shchehlov.universityschedule.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            InvalidFieldsValueException.class,
            ResourceNotFoundException.class,
            UnknownEnumValueException.class,
            IllegalUserToSaveException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest webRequest) {
        ApiError apiError;

        if (exception instanceof InvalidFieldsValueException || exception instanceof UnknownEnumValueException) {
            apiError = new ApiError(exception.getMessage());
            return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (exception instanceof ResourceNotFoundException || exception instanceof IllegalUserToSaveException) {
            apiError = new ApiError(exception.getMessage());
            return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
        }

        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

}
