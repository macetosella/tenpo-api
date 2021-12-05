package com.tenpo.dto;

import java.util.Date;

public class RequestLogDTO {

    public final String request;
    public final int httpStatus;
    public final Date date;

    public RequestLogDTO(String request, int httpStatus, Date date) {
        this.request = request;
        this.httpStatus = httpStatus;
        this.date = date;
    }
}
