package com.example.apicnpj.client.cnpj;

import com.example.apicnpj.client.cnpj.dto.WsData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cnpj-client", url = "http://ws.hubdodesenvolvedor.com.br/v2/cnpj/")
public interface CnpjClient {

    @GetMapping()
    WsData getDataByCnpj(@RequestParam(name = "cnpj") String cnpj, @RequestParam("token") String token);
}
