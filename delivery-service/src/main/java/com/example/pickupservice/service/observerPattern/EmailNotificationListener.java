package com.example.pickupservice.service.observerPattern;


import com.example.pickupservice.dto.OrderStatusChangeEvent;

import org.springframework.stereotype.Component;

//(a) Gửi email:
@Component
public  class EmailNotificationListener implements OrderStatusChangeListener {
    public void onOrderStatusChanged(OrderStatusChangeEvent event) {
        if ("DELIVERED".equals(event.getNewStatus())) {
            System.out.println("Send confirmation email for order: " + event.getOrderId());
        }
    }
}
