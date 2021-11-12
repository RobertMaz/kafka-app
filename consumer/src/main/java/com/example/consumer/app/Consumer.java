package com.example.consumer.app;

import com.example.consumer.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Consumer.class);


  @KafkaListener(topics = "${consumer.topic}", groupId = "${group.id}")
  public void consume(
      @Payload final MessageDTO msg, Acknowledgment ack) {
    logger.info("### -> Consumed message -> {}", msg);
    ack.acknowledge();
  }
}
