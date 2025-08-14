package com.example.apicnpj.client.leis;

import com.example.apicnpj.client.cnpj.dto.WsData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "leis-client", url = "https://api.gleif.org/api/v1")
public interface LeisClient {

    @GetMapping(value = "/lei-records", headers = "Accept=application/vnd.api+json")
    WsData getLeisPerPage(@RequestParam(name = "page[size]") Integer pageS, @RequestParam(name = "page[number]") Integer pageN);
}
