package com.tenpo.dto.response;

import java.util.Date;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

    public Date timestamp;
    public int code;
    public String status;
    public String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.timestamp = new Date();
    }
}
