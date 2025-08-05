package com.example.apicnpj.controller;

import com.example.apicnpj.client.cnpj.dto.Enterprise;
import com.example.apicnpj.service.CnpjService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CnpjController {

    @Autowired
    private CnpjService service;

    @GetMapping()
    public Enterprise getEnterpriseByCnpj(@RequestParam String cnpj) throws BadRequestException {
        return service.getEnterpriseByCnpj(cnpj);
    }
}
