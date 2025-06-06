package com.example.pickupservice.service;

import com.example.pickupservice.dto.CompletedDeliveriesBatch;
import com.example.pickupservice.dto.CompletedDeliveryDTO;
import com.example.pickupservice.utils.KafkaTopics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryCompletedProducer {
    private DeliveryService deliveryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCompletedDeliveries(List<CompletedDeliveryDTO> deliveries) throws JsonProcessingException {
        List<CompletedDeliveryDTO> allDeliveries = deliveryService.getAllCompletedDeliveries();
        int batchSize = 10;
        for (int i = 0; i < allDeliveries.size(); i += batchSize) {
            int end = Math.min(i + batchSize, allDeliveries.size());
            List<CompletedDeliveryDTO> batch = allDeliveries.subList(i, end);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(batch);
            System.out.println("Producter send "+ json );
            kafkaTemplate.send(KafkaTopics.DELIVERY_STATUS_TOPIC, json);
        }

    }
}
