package com.example.deliveryservice.service.observerPattern;

import com.example.deliveryservice.dto.OrderStatusChangeEvent;
import org.springframework.stereotype.Component;

@Component

public class AuditLogListener implements OrderStatusChangeListener {
    public void onOrderStatusChanged(OrderStatusChangeEvent event) {
        System.out.println("Log audit for order: " + event.getOrderId());
    }
}
