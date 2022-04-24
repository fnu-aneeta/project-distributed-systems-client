package com.distributedsystems.client.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "app.configs.client")
public class ClientConfigs {
    private String ip;
    private String email;
    private String firstName;
    private String lastName;
}
