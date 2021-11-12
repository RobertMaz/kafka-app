package com.example.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.example.consumer.app.Consumer;
import com.example.consumer.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestConsumer {

  private final String TOPIC_NAME = "test-topic";

  private Producer<String, MessageDTO> producer;

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @SpyBean
  private Consumer consumer;

  @Captor
  ArgumentCaptor<MessageDTO> userArgumentCaptor;

  @Captor
  ArgumentCaptor<Acknowledgment> ackCaptor;

  @BeforeAll
  void setUp() {
    Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
    producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(),
        new JsonSerializer<MessageDTO>()).createProducer();
  }

  @AfterAll
  void shutdown() {
    producer.close();
  }

  @Test
  void testLogKafkaMessages() throws JsonProcessingException {
    // Write a message (John Wick user) to Kafka using a test producer
//    String message = objectMapper.writeValueAsString(new MessageDTO("Message"));
    producer.send(new ProducerRecord<>(TOPIC_NAME, 0, null, new MessageDTO("msg")));
    producer.flush();
    // Read the message and assert its properties

    verify(consumer, timeout(5000).times(1))
        .consume(userArgumentCaptor.capture(), ackCaptor.capture());

    MessageDTO user = userArgumentCaptor.getValue();
    assertNotNull(user);
    assertEquals("msg", user.getMsg());
  }
}

