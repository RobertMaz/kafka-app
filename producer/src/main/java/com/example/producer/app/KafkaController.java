package com.example.producer.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

  private final Producer producer;

  @GetMapping(value = "/publish/{msg}")
  public void sendMessageToKafka(
    @PathVariable
      String msg) {
    this.producer.sendMessage(msg);
  }
}