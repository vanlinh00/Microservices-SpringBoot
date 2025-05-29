package com.example.pickupservice.service;

import com.example.pickupservice.entity.OrderPickup;
import com.example.pickupservice.repository.OrderPickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PickupService {

    @Autowired private OrderPickupRepository orderPickupRepository;
    @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    public void collectOrder(String orderId) {

        OrderPickup pickup = new OrderPickup();
        pickup.setOrderId(orderId);
        pickup.setPickupStatus("COLLECTED");
        orderPickupRepository.save(pickup);

        kafkaTemplate.send("order-picked-up", orderId);
    }
    public void createPickup() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

//        Pickup pickup = new Pickup();
//        pickup.setAddress(request.getAddress());
//        pickup.setCreatedBy(username);
//        pickup.setCreatedAt(LocalDateTime.now());
//
//        pickupRepository.save(pickup);
//
//        // lưu lịch sử
//        historyLogService.save(username, "CREATE_PICKUP", "Tạo đơn lấy hàng tại " + request.getAddress());
    }


}
