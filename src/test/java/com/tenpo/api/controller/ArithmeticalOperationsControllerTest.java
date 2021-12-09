package com.tenpo.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.hash.Hashing;
import com.tenpo.api.filter.RequestResponseLoggerFilter;
import com.tenpo.api.service.ArithmeticalOperationsService;
import com.tenpo.api.service.AuthenticationService;
import com.tenpo.api.service.RequestLoggerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ArithmeticalOperationsController.class)
class ArithmeticalOperationsControllerTest {

    @MockBean
    private ArithmeticalOperationsService arithmeticalOperationsService;
    @MockBean
    private RequestLoggerService requestLoggerService;
    @MockBean
    private AuthenticationService authenticationService;
    private RequestResponseLoggerFilter filter;
    @Autowired
    private MockMvc mockMvc;
    private ArithmeticalOperationsController controller;


    @BeforeEach
    void setUp() {
        filter = new RequestResponseLoggerFilter(requestLoggerService);
        controller = new ArithmeticalOperationsController(arithmeticalOperationsService);
    }

    @Test
    void response_with_status_200_and_response() throws Exception {
        // Arrange
        int number = 2;
        int otherNumber = 3;
        int sumResultExpected = 5;
        ResponseEntity<Integer> sumResultExpectedEntity = ResponseEntity.ok(sumResultExpected);
        String urlTemplate = "/V1/arithmetical/sum/" + number + "/" + otherNumber;
        when(arithmeticalOperationsService.sum(any())).thenReturn(sumResultExpected);

        // Act
        ResponseEntity<Integer> sumResult = controller.sum(number, otherNumber);

        // Assert
        assertEquals(sumResultExpectedEntity, sumResult);
        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
            .queryParam(Integer.toString(number), Integer.toString(otherNumber))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void prueba() {
        String user = Hashing.sha512().hashString("PEPE").toString();
        String user2 = Hashing.sha512().hashString("PEPE").toString();
        assertEquals(user, user2);
    }

}