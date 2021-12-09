package com.tenpo.api.controller;

import com.tenpo.api.exception.InvalidTokenException;
import com.tenpo.api.exception.InvalidUserException;
import com.tenpo.api.dto.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler({InvalidUserException.class, InvalidTokenException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedExceptions(Exception e) {
        return createResponseError(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(Exception e) {
        return createResponseError(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(Exception e) {
        return createResponseError(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createResponseError(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}