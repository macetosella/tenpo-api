package com.tenpo.api.service;

import com.tenpo.api.dto.SumDTO;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticalOperationsService {

    public Integer sum(SumDTO sumDTO) {
        return Integer.sum(sumDTO.number, sumDTO.otherNumber);
    }
}
