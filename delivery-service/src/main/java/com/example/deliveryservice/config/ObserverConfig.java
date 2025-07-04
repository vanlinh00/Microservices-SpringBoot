package com.example.deliveryservice.config;

import com.example.deliveryservice.service.observerPattern.OrderService;
import com.example.deliveryservice.service.observerPattern.OrderStatusChangeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ObserverConfig {

    //✅ @Bean biến method thành một object duy nhất (singleton) do Spring quản lý.
    @Bean
    public OrderService orderService(List<OrderStatusChangeListener> orderStatusChangeListeners) {
        OrderService orderService = new OrderService();
        for (OrderStatusChangeListener listener : orderStatusChangeListeners) {
            orderService.registerListener(listener);
        }
        return orderService;
    }


}
