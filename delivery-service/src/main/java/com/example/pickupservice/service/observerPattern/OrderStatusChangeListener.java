package com.example.pickupservice.service.observerPattern;

import com.example.pickupservice.dto.OrderStatusChangeEvent;

//2. interface Listener:
public interface  OrderStatusChangeListener {
    void onOrderStatusChanged(OrderStatusChangeEvent event);
}
