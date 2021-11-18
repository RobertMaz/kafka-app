package com.example.producer.app;

import lombok.RequiredArgsConstructor;
import app.common.dto.MessageFirst;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

  private final Producer producer;

  @PostMapping(value = "/publish")
  public void sendMessageToKafka(@RequestBody MessageFirst msg) {
    this.producer.sendMessage(msg);
  }
}