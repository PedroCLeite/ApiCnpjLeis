package com.example.apicnpj.client.cnpj;

import com.example.apicnpj.client.cnpj.dto.Enterprise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cnpj-client", url = "https://open.cnpja.com/office/")
public interface CnpjClient {

    @GetMapping("{cnpj}")
    Enterprise getDataByCnpj(@PathVariable(name = "cnpj") String cnpj);
}
