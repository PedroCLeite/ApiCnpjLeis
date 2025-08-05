package com.example.apicnpj.service;

import com.example.apicnpj.client.cnpj.RobustCnpjClient;
import com.example.apicnpj.client.cnpj.dto.Enterprise;
import com.example.apicnpj.utils.CnpjUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CnpjService {

    @Autowired
    private RobustCnpjClient robustCnpjClient;

    public Enterprise getEnterpriseByCnpj(String cnpj) throws BadRequestException {
        if (!CnpjUtil.isValidCnpj(cnpj)){
            throw new BadRequestException("CNPJ inválido.");
        }

        return robustCnpjClient.getEnterpriseByCnpj(CnpjUtil.sanitizeCnpj(cnpj));
    }
}
