package com.tenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String errorMessage) {
        super(errorMessage);
    }
}