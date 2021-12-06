package com.tenpo.dto;

import java.util.Date;

public class RequestLogDTO {

    public final String request;
    public final Date date;

    public RequestLogDTO(String request, Date date) {
        this.request = request;
        this.date = date;
    }
}
