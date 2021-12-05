package com.tenpo.controller;

import com.tenpo.dto.SumDTO;
import com.tenpo.service.IArithmeticalService;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/arithmetical")
public class ArithmeticalController {

    private static final Logger LOG = LogManager.getLogger(ArithmeticalController.class);
    private final IArithmeticalService arithmeticalService;

    @Autowired
    public ArithmeticalController(IArithmeticalService arithmeticalService) {
        this.arithmeticalService = arithmeticalService;
    }

    @GetMapping(path = "/sum/{number}/{otherNumber}")
    @ResponseBody
    public int sum(@PathVariable int number, @PathVariable int otherNumber) {
        SumDTO sumDTO = SumDTO.create(number, otherNumber);
        final int sumResult = arithmeticalService.sum(sumDTO);
        LOG.info("Logging Response : {}", sumResult);
        return sumResult;

    }
}
