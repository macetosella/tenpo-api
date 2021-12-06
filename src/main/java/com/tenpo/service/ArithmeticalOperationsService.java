package com.tenpo.service;

import com.tenpo.dto.SumDTO;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticalOperationsService {

    public Integer sum(SumDTO sumDTO) {
        return Integer.sum(sumDTO.number, sumDTO.otherNumber);
    }
}
