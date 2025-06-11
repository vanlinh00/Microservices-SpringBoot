package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.CompletedDeliveryDTO;

import java.util.List;

public interface DeliveryService {
    List<CompletedDeliveryDTO> getAllCompletedDeliveries();

}
