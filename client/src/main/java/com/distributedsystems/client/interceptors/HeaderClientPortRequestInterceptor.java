package com.distributedsystems.client.interceptors;

import com.distributedsystems.client.contexts.SpringContext;
import com.distributedsystems.client.services.ServerPortService;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HeaderClientPortRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ServerPortService serverPortService = SpringContext.getBean(ServerPortService.class);
        request.getHeaders().set("x-client-port", String.valueOf(serverPortService.getPort()));
        return execution.execute(request, body);
    }
}
