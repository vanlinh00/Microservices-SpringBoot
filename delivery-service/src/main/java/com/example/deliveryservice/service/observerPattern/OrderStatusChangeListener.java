package com.example.deliveryservice.service.observerPattern;

import com.example.deliveryservice.dto.OrderStatusChangeEvent;

//2. interface Listener:
public interface  OrderStatusChangeListener {
    void onOrderStatusChanged(OrderStatusChangeEvent event);
}
