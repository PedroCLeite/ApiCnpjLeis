package com.example.apicnpj.client.leis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GleifResponse {

    private List<GleifData> data;
}
