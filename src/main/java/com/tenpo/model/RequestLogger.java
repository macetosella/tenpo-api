package com.tenpo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long requestId;
    public String request;
    public Date date;

    public RequestLogger() {

    }

    public RequestLogger(String request, Date date) {
        this.request = request;
        this.date = date;
    }

    @Override
    public String toString() {
        return "RequestLogger{" +
            "requestId=" + requestId +
            ", request='" + request + '\'' +
            ", date=" + date +
            '}';
    }
}
