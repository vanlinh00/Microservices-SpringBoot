package com.example.pickupservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompletedDeliveriesBatch {
    private List<CompletedDeliveryDTO> completedDeliveries;
}
