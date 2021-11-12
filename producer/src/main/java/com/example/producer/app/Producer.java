package com.example.producer.app;

import com.example.producer.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

  @Value("${producer.topic}")
  private String TOPIC;

  private final KafkaTemplate<String, MessageDTO> kafkaTemplate;

  public void sendMessage(MessageDTO message) {
    logger.info("### -> Producing message -> {}", message);
    this.kafkaTemplate.send(TOPIC, message);
  }
}
