package com.example.apicnpj.client.cnpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsResult {

    private String nome;
    private String fantasia;
    private String cep;
    private String email;
    private String telefone;
    private WsActivity atividade;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class WsActivity {
        private String text;
    }
}
