package com.example.apicnpj.client.cnpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsData {

    private String status;
    private WsResult result;

}
