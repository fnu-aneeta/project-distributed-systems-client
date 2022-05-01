package com.distributedsystems.client.controllers;

import com.distributedsystems.client.requests.MessageDetails;
import com.distributedsystems.client.services.MessageSenderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/send/message")
public class MessageSenderController {

  private final MessageSenderService messageSenderService;

  @PostMapping
  void sendMessage(@Valid @RequestBody final MessageDetails message) {
    messageSenderService.sendMessage(message);
  }
}
