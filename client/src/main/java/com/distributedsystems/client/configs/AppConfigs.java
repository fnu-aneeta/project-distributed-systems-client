package com.distributedsystems.client.configs;

import com.distributedsystems.client.interceptors.HeaderClientPortRequestInterceptor;
import com.distributedsystems.client.interceptors.HeaderRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class AppConfigs {

    @Value("${app.configs.client.ip}")
    private String clientHostIp;


    @Bean
    RestTemplate restTemplate(){
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderRequestInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HeaderRequestInterceptor("x-client-ip", clientHostIp));
        interceptors.add(new HeaderClientPortRequestInterceptor());

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
