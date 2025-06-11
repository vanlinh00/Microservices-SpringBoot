package com.example.deliveryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//1. OrderStatusChangeEvent – Đối tượng sự kiện:
@AllArgsConstructor
@Data
public class OrderStatusChangeEvent {
    private  String orderId;
    private  String newStatus;
}
