package com.example.producer.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

  private final Producer producer;

  @GetMapping(value = "/publish/{msg}")
  public ResponseEntity<String> sendMessageToKafka(
    @PathVariable
      String msg) {
    this.producer.sendMessage(msg);
    String host;
    try {
      host = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(String.format("success. Host-%s", host), HttpStatus.OK);
  }
}