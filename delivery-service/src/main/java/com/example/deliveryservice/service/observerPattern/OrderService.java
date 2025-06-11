package com.example.deliveryservice.service.observerPattern;

import com.example.deliveryservice.dto.OrderStatusChangeEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<OrderStatusChangeListener> listeners = new ArrayList<>();

    public void registerListener(OrderStatusChangeListener listener) {
        listeners.add(listener);
    }

        public void updateOrderStatus(String orderId, String newStatus) {

        // Giả sử gọi DB update ở đây
        System.out.println("Updating order " + orderId + " to status: " + newStatus);

        // Gửi thông báo đến tất cả listener
        OrderStatusChangeEvent event = new OrderStatusChangeEvent(orderId, newStatus);

        for (OrderStatusChangeListener listener : listeners) {
            listener.onOrderStatusChanged(event);
        }
    }
}
