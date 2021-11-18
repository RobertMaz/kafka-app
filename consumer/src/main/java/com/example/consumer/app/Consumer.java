package com.example.consumer.app;

import app.common.dto.MessageFirst;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
  public void consumerFirst(ConsumerRecord<String, MessageFirst> first,
      @Payload final MessageFirst msg, Acknowledgment ack) throws InterruptedException {
    logger.info("### -> Consumed message -> {}. partition -> {}", msg,
        first.partition());
    Thread.sleep(10000);
    ack.acknowledge();
  }
}
