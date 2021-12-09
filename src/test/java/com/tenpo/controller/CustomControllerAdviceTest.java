package com.tenpo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.tenpo.dto.response.ErrorResponse;
import com.tenpo.exception.InvalidUserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

class CustomControllerAdviceTest {

    private CustomControllerAdvice customControllerAdvice;

    @BeforeEach
    void beforeEach() {
        customControllerAdvice = new CustomControllerAdvice();
    }

    @Test
    void handleUnauthorizedExceptions() {
        InvalidUserException invalidUserException = new InvalidUserException("pepe");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "pepe");
        ResponseEntity<ErrorResponse> errorResponseResponseEntityExpected = new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = customControllerAdvice.handleUnauthorizedExceptions(invalidUserException);

        assertEquals(errorResponseResponseEntityExpected.getStatusCode(), errorResponseResponseEntity.getStatusCode());
        assertEquals(errorResponseResponseEntityExpected.getBody().message, errorResponseResponseEntity.getBody().message);
    }

    @Test
    void handleDataIntegrityViolationException() {
        DataIntegrityViolationException dataIntegrityViolationException = new DataIntegrityViolationException("pepe");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, "pepe");
        ResponseEntity<ErrorResponse> errorResponseResponseEntityExpected = new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = customControllerAdvice.handleDataIntegrityViolationException(dataIntegrityViolationException);

        assertEquals(errorResponseResponseEntityExpected.getStatusCode(), errorResponseResponseEntity.getStatusCode());
        assertEquals(errorResponseResponseEntityExpected.getBody().message, errorResponseResponseEntity.getBody().message);
    }

    @Test
    void handleMethodArgumentNotValidException() {
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "pepe");
        ResponseEntity<ErrorResponse> errorResponseResponseEntityExpected = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = customControllerAdvice.handleMethodArgumentNotValidException(methodArgumentNotValidException);

        assertEquals(errorResponseResponseEntityExpected.getStatusCode(), errorResponseResponseEntity.getStatusCode());
    }
}