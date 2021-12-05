package com.tenpo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tenpo.service.IArithmeticalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ArithmeticalController.class)
class ArithmeticalControllerTest {

    @MockBean
    private IArithmeticalService arithmeticalService;

    @Autowired
    private MockMvc mockMvc;

    private ArithmeticalController controller;

    @BeforeEach
    void setUp() {
        controller = new ArithmeticalController(arithmeticalService);
    }

    @Test
    void response_with_status_200_and_response() throws Exception {
        // Arrange
        int number = 2;
        int otherNumber = 3;
        int sumResultExpected = 5;
        String urlTemplate = "/V1/arithmetical/sum/" + number + "/" + otherNumber;
        when(arithmeticalService.sum(any())).thenReturn(5);

        // Act
        int sumResult = controller.sum(number, otherNumber);

        // Assert
        assertEquals(sumResultExpected, sumResult);
        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
            .queryParam(Integer.toString(number), Integer.toString(otherNumber))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}