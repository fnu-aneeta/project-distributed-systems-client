package com.distributedsystems.client.services;

import com.distributedsystems.client.configs.ClientConfigs;
import com.distributedsystems.client.resources.UserResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConnectMasterService {
    private final RestTemplate restTemplate;

    @Value("${app.configs.master.host}")
    private String masterHost;

    @Value("${app.configs.master.user-register.endpoint}")
    private String userRegisterEndpoint;

    private final ClientConfigs clientConfigs;

    private void connectWithMaster() {
        final String resourceUrl = getUserRegisterUrl();

        final UserResource userResource = UserResource.builder()
                .email(clientConfigs.getEmail())
                .firstName(clientConfigs.getFirstName())
                .lastName(clientConfigs.getLastName())
                .build();
        try {
            ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, new HttpEntity<>(userResource), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("User {} is connected with master", userResource.getEmail());
            }
        } catch (ResourceAccessException e) {
            log.error("Unable to build connection with master: {}", e.getMessage());
            e.printStackTrace();
        }

    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        connectWithMaster();
    }

    private String getUserRegisterUrl() {
        return String.format("%s%s", masterHost, userRegisterEndpoint);
    }
}
