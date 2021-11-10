package com.example.producer.app;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  private static final String TOPIC = "users";

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    logger.info("### -> Producing message -> {}", message);
    this.kafkaTemplate.send(TOPIC, message);
  }
}
