package com.distributedsystems.client.requests;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDetails {
  @NotBlank(message = "Message 'body' can't be empty")
  private String body;
  @NotBlank(message = "'fromEmail' is required to send message.")
  private String fromEmail;
  @NotBlank(message = "'toEmail' is required to send message.")
  private String toEmail;
}
