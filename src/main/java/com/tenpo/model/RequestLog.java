package com.tenpo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long requestId;
    public String request;
    public int httpStatus;
    public Date date;

    public RequestLog() {

    }

    public RequestLog(String request, int httpStatus, Date date) {
        this.request = request;
        this.httpStatus = httpStatus;
        this.date = date;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
            "requestId=" + requestId +
            ", request='" + request + '\'' +
            ", httpStatus=" + httpStatus +
            ", date=" + date +
            '}';
    }
}
