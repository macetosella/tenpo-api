package com.tenpo.service;

import com.tenpo.dto.SumDTO;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticalOperationsService {

    public int sum(SumDTO sumDTO) {
        return sumDTO.number + sumDTO.otherNumber;
    }
}
