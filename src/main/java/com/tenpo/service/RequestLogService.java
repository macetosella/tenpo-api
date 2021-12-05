package com.tenpo.service;

import com.tenpo.dto.RequestLogDTO;
import com.tenpo.model.RequestLog;
import com.tenpo.repository.RequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RequestLogService {

    private static final Logger LOG = LogManager.getLogger(RequestLogService.class);
    private final RequestRepository repository;

    @Autowired
    public RequestLogService(RequestRepository repository) {
        this.repository = repository;
    }

    @Async
    public void save(RequestLogDTO requestLogDTO) {
        RequestLog requestLog = new RequestLog(requestLogDTO.request, requestLogDTO.httpStatus, requestLogDTO.date);
        RequestLog requestLogSaved = repository.save(requestLog);
        LOG.info("Saved request : {} ", requestLogSaved);
    }

}
