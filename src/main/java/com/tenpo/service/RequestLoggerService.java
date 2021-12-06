package com.tenpo.service;

import com.tenpo.dto.RequestLogDTO;
import com.tenpo.model.RequestLog;
import com.tenpo.repository.RequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RequestLoggerService {

    private static final Logger LOG = LogManager.getLogger(RequestLoggerService.class);
    private final RequestRepository repository;

    @Autowired
    public RequestLoggerService(RequestRepository repository) {
        this.repository = repository;
    }

    @Async
    public void save(RequestLogDTO requestLogDTO) {
        RequestLog requestLog = new RequestLog(requestLogDTO.request, requestLogDTO.date);
        RequestLog requestLogSaved = repository.save(requestLog);
        LOG.info("Saved request : {} ", requestLogSaved);
    }

    public Page<RequestLog> findAll(int page, int size) {
        LOG.info("Finding all requests page : {}, size : {} ", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

}
