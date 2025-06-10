package com.example.pickupservice.service.observerPattern;

import com.example.pickupservice.dto.OrderStatusChangeEvent;
import org.springframework.stereotype.Component;

import java.util.Observer;

@Component

public class AuditLogListener implements OrderStatusChangeListener {
    public void onOrderStatusChanged(OrderStatusChangeEvent event) {
        System.out.println("Log audit for order: " + event.getOrderId());
    }
}
