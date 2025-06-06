package com.example.pickupservice.service;

import com.example.pickupservice.dto.CompletedDeliveryDTO;

import java.util.List;

public interface DeliveryService {
    List<CompletedDeliveryDTO> getAllCompletedDeliveries();

}
