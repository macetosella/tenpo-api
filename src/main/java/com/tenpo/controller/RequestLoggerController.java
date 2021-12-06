package com.tenpo.controller;

import com.tenpo.model.RequestLog;
import com.tenpo.service.RequestLoggerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/logger")
public class RequestLoggerController {

    private static final Logger LOG = LogManager.getLogger(RequestLoggerController.class);
    private final RequestLoggerService requestLoggerService;

    @Autowired
    public RequestLoggerController(RequestLoggerService requestLoggerService) {
        this.requestLoggerService = requestLoggerService;
    }

    @GetMapping(path = "/history/{page}/{size}")
    @ResponseBody
    public Page<RequestLog> history(@PathVariable int page, @PathVariable int size) {
        Page<RequestLog> requestLogPage = requestLoggerService.findAll(page, size);
        LOG.info("[history] Response : {}", requestLogPage);
        return requestLogPage;
    }
}
