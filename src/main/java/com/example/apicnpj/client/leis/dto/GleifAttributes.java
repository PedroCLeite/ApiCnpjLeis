package com.example.apicnpj.client.leis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GleifAttributes {
    private String lei;
    private GleifEntity entity;
}
