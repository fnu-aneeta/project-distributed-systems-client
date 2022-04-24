package com.distributedsystems.client.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResource {
    private String id;

    @NotBlank(message = "User email is required.")
    private String email;

    @NotBlank(message = "User first name is required.")
    private String firstName;

    @NotBlank(message = "User last name is required.")
    private String lastName;
}
