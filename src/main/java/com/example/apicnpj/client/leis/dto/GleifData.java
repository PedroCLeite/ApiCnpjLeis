package com.example.apicnpj.client.leis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GleifData {
    private String id;
    private GleifAttributes attributes;
}