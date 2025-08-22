package com.example.apicnpj.service;

import com.example.apicnpj.dto.LeiDto;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeisService {

    private CsvService csvService;

    public LeisService(CsvService csvService) {
        this.csvService = csvService;
    }

    @Cacheable("leis")
    public List<LeiDto> readCsv() throws CsvValidationException, IOException {
        Resource resource  = new ClassPathResource("leis-data.csv");
        Path filePath = resource.getFile().toPath();

        return csvService.readCsv(filePath);
    }

}
