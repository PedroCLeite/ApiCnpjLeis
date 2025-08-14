package com.example.apicnpj.client.cnpj;

import com.example.apicnpj.client.RobustClient;
import com.example.apicnpj.client.cnpj.dto.WsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RobustCnpjClient extends RobustClient {

    private static final String CNPJ_CLIENT_NAME = "cnpj";

    @Value("${TOKEN_RECEITAWS}")
    private static String TOKEN_RECEITAWS;

    @Autowired
    private CnpjClient cnpjClient;

    public RobustCnpjClient() {
        super(CNPJ_CLIENT_NAME);
    }

    @Cacheable("enterprise")
    public WsData getEnterpriseByCnpj(String cnpj) {
        return super.robustCall(() -> cnpjClient.getDataByCnpj(cnpj, TOKEN_RECEITAWS));
    }
}
