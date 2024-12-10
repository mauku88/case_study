package com.example.product.consumer;

import com.example.product.dto.KafkaPayload;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private static final String TOPIC_NAME = "order-created";
    private static final String GROUP_ID = "GroupId";
    private static final String CONTAINER_FACTORY = "ContainerFactory";

    private final ProductService productService;
    private final ObjectMapper MAPPER = new ObjectMapper();

    @KafkaListener(topics = {TOPIC_NAME}, groupId = GROUP_ID, containerFactory = CONTAINER_FACTORY)
    public void listener(@Payload Object event, ConsumerRecord c) throws Exception {
        // Kafka kaydinin degerini bir dize olarak al
        String value = (String) c.value();

        // Deger dizesini JSON dugumune donustur
        JsonNode payload = MAPPER.readTree(value);
        log.info("JSON NODE: {}", payload);

        // JSON'dan KafkaPayload nesnesine donusturme islemi
        KafkaPayload kafkaPayload = MAPPER.readValue(payload.get("payload").asText(), KafkaPayload.class);
        log.info("KafkaPayload: {}", kafkaPayload);
        log.info("KafkaPayload getId: {}", kafkaPayload.getProductId());

        productService.updateStock(kafkaPayload.getProductId(),kafkaPayload.getStock());

        // Outbox'tan belirli bir islemi silme islemi
        productService.deleteProcessByIdFromOutbox(kafkaPayload.getId());
    }
}
