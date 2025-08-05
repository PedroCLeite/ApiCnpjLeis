package com.example.apicnpj.utils;

import org.apache.commons.lang3.StringUtils;

public class CnpjUtil {

    private static final int CNPJ_LENGTH = 14;
    private static final char ZERO_PAD_CHAR = '0';

    public static String sanitizeCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        return StringUtils.leftPad(cnpj, CNPJ_LENGTH, ZERO_PAD_CHAR);
    }


    public static boolean isValidCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma1 = 0;
        for (int i = 0; i < 12; i++) {
            soma1 += Character.getNumericValue(cnpj.charAt(i)) * multiplicadores1[i];
        }
        int resto1 = soma1 % 11;
        int digito1 = resto1 < 2 ? 0 : 11 - resto1;

        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma2 = 0;
        for (int i = 0; i < 13; i++) {
            soma2 += Character.getNumericValue(cnpj.charAt(i)) * multiplicadores2[i];
        }
        int resto2 = soma2 % 11;
        int digito2 = resto2 < 2 ? 0 : 11 - resto2;

        return cnpj.charAt(12) == Character.forDigit(digito1, 10) && cnpj.charAt(13) == Character.forDigit(digito2, 10);
    }
}
