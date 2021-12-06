package com.tenpo.filter;

import com.tenpo.dto.RequestLogDTO;
import com.tenpo.service.RequestLoggerService;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseLogFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(RequestResponseLogFilter.class);
    private final RequestLoggerService requestLoggerService;

    @Autowired
    public RequestResponseLogFilter(RequestLoggerService requestLoggerService) {
        this.requestLoggerService = requestLoggerService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        LOG.info("Request  {} : {}", req.getMethod(), req.getRequestURI());

        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(req.getMethod()).append(":").append(req.getRequestURI());
        RequestLogDTO requestLogDTO = new RequestLogDTO(requestBuilder.toString(), new Date());

        requestLoggerService.save(requestLogDTO);

        chain.doFilter(request, response);

        LOG.info("Status Response : {}", res.getStatus());

    }
}
