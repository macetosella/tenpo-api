package com.tenpo.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tenpo.api.dto.SumDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArithmeticalOperationsServiceTest {

    private ArithmeticalOperationsService service;

    @BeforeEach
    void setUp() {
        service = new ArithmeticalOperationsService();
    }

    @Test
    void sum_two_numbers() {
        // Arrange
        int number = 2;
        int otherNumber = 3;
        int sumResultExpected = 5;
        SumDTO sumDTO = SumDTO.create(number, otherNumber);

        // Act
        int sumResult = service.sum(sumDTO);

        // Assert
        assertEquals(sumResultExpected, sumResult);
    }
}

