package com.distributedsystems.client.services;

import com.distributedsystems.client.requests.MessageDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageSenderService {

  private final JmsTemplate jmsTemplate;
  private final ObjectMapper objectMapper;

  public void sendMessage(final MessageDetails message) {
    final String toEmail = message.getToEmail();
    try {
      log.info("Sending message to: {}", toEmail);
      final String jsonStringMessage = objectMapper.writeValueAsString(message);
      jmsTemplate.convertAndSend(toEmail, jsonStringMessage);
    } catch (Exception e) {
      log.error("Cannot send message to: {}, error: {}", toEmail, e.getMessage());
    }
  }
}
