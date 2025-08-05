package com.example.apicnpj.config;

import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import feign.Client;
import org.springframework.context.annotation.Bean;


@Configuration
public class FeignClientConfig {

    @Bean
    public Client feignClient() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] xcs, String string) {}
                    public void checkServerTrusted(X509Certificate[] xcs, String string) {}
                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        return new Client.Default(sslContext.getSocketFactory(), (hostname, session) -> true);
    }
}
