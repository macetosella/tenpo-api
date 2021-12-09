package com.tenpo.api.controller;

import com.tenpo.api.service.ArithmeticalOperationsService;
import com.tenpo.api.dto.SumDTO;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/arithmetical")
public class ArithmeticalOperationsController {

    private static final Logger LOG = LogManager.getLogger(ArithmeticalOperationsController.class);
    private final ArithmeticalOperationsService arithmeticalOperationsService;

    @Autowired
    public ArithmeticalOperationsController(ArithmeticalOperationsService arithmeticalOperationsService) {
        this.arithmeticalOperationsService = arithmeticalOperationsService;
    }

    @GetMapping(path = "/sum/{number}/{otherNumber}")
    @ResponseBody
    public ResponseEntity<Integer> sum(@PathVariable @NotNull int number, @PathVariable @NotNull int otherNumber) {
        LOG.info("[sum] Request : number: {}, otherNumber: {}", number, otherNumber);
        SumDTO sumDTO = SumDTO.create(number, otherNumber);
        final Integer sumResult = arithmeticalOperationsService.sum(sumDTO);
        LOG.info("[sum] Response : {}", sumResult);

        return ResponseEntity.ok(sumResult);

    }
}
