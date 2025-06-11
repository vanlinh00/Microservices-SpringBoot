package com.example.deliveryservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompletedDeliveriesBatch {
    private List<CompletedDeliveryDTO> completedDeliveries;
}
