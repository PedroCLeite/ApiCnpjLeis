package com.example.apicnpj.client.cnpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int municipality;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String details;
    private String zip;
    private Country country;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Country {
        private int id;
        private String name;
    }
}
