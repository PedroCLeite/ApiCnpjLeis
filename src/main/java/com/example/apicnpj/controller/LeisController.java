package com.example.apicnpj.controller;

import com.example.apicnpj.dto.LeiDto;
import com.example.apicnpj.service.LeisService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LeisController {

    private LeisService leisService;

    public LeisController(LeisService leisService) {
        this.leisService = leisService;
    }

    @GetMapping("/leis")
    public List<LeiDto> readCsv() throws CsvValidationException, IOException {
        return leisService.readCsv();
    }

}
