package com.example.consumer.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Consumer.class);

  @KafkaListener(topics = "users", groupId = "first")
  public void consume(
      @Payload final String msgBody, Acknowledgment ack) {
    logger.info("### -> Consumed message -> {}", msgBody);
    ack.acknowledge();
  }
}
