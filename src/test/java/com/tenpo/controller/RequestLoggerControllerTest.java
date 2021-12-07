package com.tenpo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.model.RequestLogger;
import com.tenpo.service.AuthenticationService;
import com.tenpo.service.RequestLoggerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(RequestLoggerController.class)
class RequestLoggerControllerTest {

    @MockBean
    private RequestLoggerService requestLoggerService;
    @MockBean
    private AuthenticationService authenticationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void history() throws Exception {
        int page = 2;
        int size = 1;
        Page<RequestLogger> requestLoggerPage = Page.empty();
        when(requestLoggerService.findAll(page, size)).thenReturn(requestLoggerPage);
        String urlTemplate = "/V1/logger/history/" + page + "/" + size;

        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
            .queryParam(Integer.toString(page), Integer.toString(size))
            .content(new ObjectMapper().writeValueAsString((requestLoggerPage)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }
}