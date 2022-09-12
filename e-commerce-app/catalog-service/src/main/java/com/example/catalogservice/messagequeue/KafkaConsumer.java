package com.example.catalogservice.messagequeue;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = (String) map.get("productId");
        Integer qty = (Integer) map.get("qty");
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);
        if (catalogEntity != null) {
            catalogEntity.setStock(catalogEntity.getStock() - qty);
            catalogRepository.save(catalogEntity);
        }
    }
}
