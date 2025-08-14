package com.example.apicnpj.client.leis;

import com.example.apicnpj.client.RobustClient;
import com.example.apicnpj.client.cnpj.dto.WsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RobustLeisClient extends RobustClient {

    private static final String LEIS_CLIENT_NAME = "leis";

    @Autowired
    private LeisClient leisClient;

    public RobustLeisClient() {
        super(LEIS_CLIENT_NAME);
    }

    @Cacheable("leis")
    public WsData getLeisPerPage(Integer pageS, Integer pageN) {
        return super.robustCall(() -> leisClient.getLeisPerPage(pageS, pageN));
    }
}
