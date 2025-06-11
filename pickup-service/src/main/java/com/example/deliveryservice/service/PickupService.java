package com.example.deliveryservice.service;

import com.example.deliveryservice.entity.OrderPickup;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PickupService {


    public void collectOrder(String orderId) {

        OrderPickup pickup = new OrderPickup();
        pickup.setOrderId(orderId);
        pickup.setPickupStatus("COLLECTED");
    }

    public void createPickup() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

    }


    private final AtomicInteger counter = new AtomicInteger(0);
    private long startTime = 0;


    /*
    Trong Kafka, mỗi message có metadata như:
            1. partition
            2. offset
            3. topic
            4. key
            5. timestamp
     */
    /*
    @KafkaListener(
            topics = KafkaTopics.DELIVERY_STATUS_TOPIC,
            groupId = "delivery_group",
            concurrency = "3" // 3 consumer thread chạy song song
            // autoStartup = "false" // <- Dòng này ngăn không cho listener chạy lúc khởi động

    )

    public void  consume(String message,
                         @Header("kafka_receivedPartitionId") int partition,
                         @Header("kafka_offset") long offset,
                         @Header("kafka_receivedTopic") String topic) {
        try {
            System.out.println("message" + message);
            if (counter.get() == 0) {
                startTime = System.currentTimeMillis();
            }
            System.out.println("message" + message);

            // Bỏ dấu ngoặc kép ngoài cùng và decode lại JSON
            String fixedJson = message.substring(1, message.length() - 1).replace("\\\"", "\"");

            ObjectMapper objectMapper = new ObjectMapper();
            List<CompletedDeliveryDTO> items = objectMapper.readValue(fixedJson, new TypeReference<List<CompletedDeliveryDTO>>() {
            });

            // In thử
            for (CompletedDeliveryDTO item : items) {
                System.out.println(item.getLadingCode() + " - " + item.getPostman() + " - " + item.getStatus());
            }
            System.out.println("---- Kafka Message Info ----");

            System.out.println("Topic: " + topic);
            System.out.println("Partition: " + partition);
            System.out.println("Offset: " + offset);


//
//            // Giả lập xử lý mất 100ms
//            Thread.sleep(100);
//
//            int processed = counter.incrementAndGet();
//
//            if (processed == TOTAL_MESSAGES) {
//                long endTime = System.currentTimeMillis();
//                System.out.println("=== Tổng thời gian xử lý " + TOTAL_MESSAGES + " tin nhắn: " + (endTime - startTime) + " ms ===");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 */
    @KafkaListener(topics = "delivery-status-topic-3", groupId = "delivery_group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    /*

    🔧 Bước 1: Truy cập vào container Kafka

docker exec -it kafka_demo bash

    🛠️ Bước 1: Kiểm tra topic đã tồn tại chưa

kafka-topics.sh --bootstrap-server localhost:9092 --list

🛠️ Bước 2: Gửi lại message sau khi topic đã tồn tại
kafka-console-producer.sh \
  --bootstrap-server localhost:9092 \
  --topic delivery-status-topic-3

Gõ message rồi Enter:

{"orderId":123, "status":"SHIPPED"}
    */


}
