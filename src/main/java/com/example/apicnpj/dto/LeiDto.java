package com.example.apicnpj.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeiDto {

    private Integer ano;
    private String documento;
    private String numero;
    private String ato;
    private String ementa;
    private String area;
    private String assunto;
    private String link;

}
