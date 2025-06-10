package com.example.pickupservice.controller;

import com.example.pickupservice.dto.CompletedDeliveriesBatch;
import com.example.pickupservice.dto.OrderStatusChangeEvent;
import com.example.pickupservice.service.DeliveryCompletedProducer;
import com.example.pickupservice.service.observerPattern.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")  // Base path cho API
@AllArgsConstructor
public class DeliveryController {
    private final DeliveryCompletedProducer producer;
    private final OrderService orderService;

    // Lấy danh sách tất cả đơn giao hàng
    @GetMapping
    public ResponseEntity<String> getAllDeliveries() {
        return ResponseEntity.ok("test ok");
    }

    @PostMapping("/complete-batch")
    public ResponseEntity<?> sendBatchFromClient(@RequestBody CompletedDeliveriesBatch batch) throws JsonProcessingException {
        producer.sendCompletedDeliveries(batch.getCompletedDeliveries());
        return ResponseEntity.ok("Batch sent from client");
    }

    @PostMapping("/update-status")
    public ResponseEntity<String> updateOrderStatus(@RequestBody OrderStatusChangeEvent request) {
        orderService.updateOrderStatus(request.getOrderId(), request.getNewStatus());
        return ResponseEntity.ok("Order status updated successfully");
    }

}
