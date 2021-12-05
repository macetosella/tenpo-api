package com.tenpo.service.implementation;

import com.tenpo.dto.SumDTO;
import com.tenpo.service.IArithmeticalService;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticalService implements IArithmeticalService {

    @Override
    public int sum(SumDTO sumDTO) {
        return sumDTO.number + sumDTO.otherNumber;
    }
}
