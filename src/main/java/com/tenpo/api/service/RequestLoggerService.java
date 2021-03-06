package com.tenpo.api.service;

import com.tenpo.api.dto.RequestLoggerDTO;
import com.tenpo.api.model.RequestLogger;
import com.tenpo.api.repository.RequestRepository;
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
    public void save(RequestLoggerDTO requestLoggerDTO) {
        RequestLogger requestLogger = new RequestLogger(requestLoggerDTO.request, requestLoggerDTO.date);
        RequestLogger requestLoggerSaved = repository.save(requestLogger);
        LOG.info("Saved request : {} ", requestLoggerSaved);
    }

    public Page<RequestLogger> findAll(int page, int size) {
        LOG.info("Finding all requests page : {}, size : {} ", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

}
