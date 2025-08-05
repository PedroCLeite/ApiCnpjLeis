package com.example.apicnpj.client.cnpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    private Address address;
    private List<Phone> phones;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Phone {
        private String type;
        private String area;
        private String number;
    }
}
