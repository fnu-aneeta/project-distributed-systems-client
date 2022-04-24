package com.distributedsystems.client.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ErrorResource {
    private HttpStatus status;
    private Integer code;
    private String message;
    private List<String> errorDetails;

    public ErrorResource() {
        super();
    }

    public ErrorResource(String message) {
        this.message = message;
    }

    public ErrorResource(final HttpStatus status, final String message, final List<String> errorDetailsList) {
        super();
        this.status = status;
        this.code = status.value();
        this.message = message;
        this.errorDetails = errorDetailsList;
    }

    public ErrorResource(final HttpStatus status, final String message, final String errorDetails) {
        super();
        this.status = status;
        this.code = status.value();
        this.message = message;
        this.errorDetails = List.of(errorDetails);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }
}
