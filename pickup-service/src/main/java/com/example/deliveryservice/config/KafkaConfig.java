package com.example.deliveryservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class KafkaConfig {
    @Bean
    public List<NewTopic> topics() {
        return List.of(
                new NewTopic("delivery-status-topic-1-partition", 1, (short) 1),
                new NewTopic("delivery-status-topic-3-partition", 3, (short) 1)
        );
    }
}
