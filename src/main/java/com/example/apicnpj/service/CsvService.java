package com.example.apicnpj.service;

import com.example.apicnpj.dto.LeiDto;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public CsvService() {}

    public List<LeiDto> readCsv(Path filePath) throws CsvValidationException, IOException {
        List<LeiDto> leis = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath.toFile()))
                .withCSVParser(parser)
                .build();
        String[] line;
        reader.readNext();
        while ((line = reader.readNext()) != null){
            leis.add(new LeiDto(
                    Integer.parseInt(line[0]),
                    line[1],
                    line[2],
                    line[3],
                    line[4],
                    line[5],
                    line[6],
                    line[7]
            ));
        }

        return leis;
    }
}
