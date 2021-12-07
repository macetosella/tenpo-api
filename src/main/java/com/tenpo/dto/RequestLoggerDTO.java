package com.tenpo.dto;

import java.util.Date;

public class RequestLoggerDTO {

    public final String request;
    public final Date date;

    public RequestLoggerDTO(String request, Date date) {
        this.request = request;
        this.date = date;
    }
}
