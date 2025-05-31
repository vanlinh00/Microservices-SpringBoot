//package com.example.pickupservice.config;
//
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Bean
//    public List<NewTopic> topics() {
//        return List.of(
//                new NewTopic("delivery-status-topic-1-partition", 1, (short) 1),
//                new NewTopic("delivery-status-topic-3-partition", 3, (short) 1)
//        );
//    }
//
//
//}
