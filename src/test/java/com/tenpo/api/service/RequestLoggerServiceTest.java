package com.tenpo.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tenpo.api.dto.RequestLoggerDTO;
import com.tenpo.api.model.RequestLogger;
import com.tenpo.api.repository.RequestRepository;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class RequestLoggerServiceTest {

    @Mock
    private RequestRepository repository;

    @Test
    void save() {
        RequestLoggerDTO requestLoggerDTO = new RequestLoggerDTO("/V1/sarasa", new Date());
        RequestLoggerService requestLoggerService = new RequestLoggerService(repository);
        when(repository.save(any())).thenReturn(null);

        Executable ex = () -> requestLoggerService.save(requestLoggerDTO);

        assertDoesNotThrow(ex);
    }

    @Test
    void findAll() {
        int page = 1;
        int size = 1;
        Page<RequestLogger> pageEmpty = Page.empty();
        RequestLoggerService requestLoggerService = new RequestLoggerService(repository);
        when(repository.findAll((Pageable) any())).thenReturn(pageEmpty);

        Executable ex = () -> requestLoggerService.findAll(page, size);

        assertDoesNotThrow(ex);
    }
}