package com.example.deliveryservice.service.observerPattern;


import com.example.deliveryservice.dto.OrderStatusChangeEvent;

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
