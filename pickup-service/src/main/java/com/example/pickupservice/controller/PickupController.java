package com.example.pickupservice.controller;

import com.example.pickupservice.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pickup")
public class PickupController {

    @Autowired
    private PickupService pickupService;

    @PostMapping("/collect")
    public ResponseEntity<String> collect(@RequestParam String orderId) {
        pickupService.collectOrder(orderId);
        return ResponseEntity.ok("Order collected: " + orderId);
    }

    @GetMapping("createPickup")
    public ResponseEntity<?> createPickup() {
        pickupService.createPickup();
        return ResponseEntity.ok("Pickup created successfully.");
    }

    @GetMapping("/ping")
    public String ping() {

        return "pickup OK";
    }


}
