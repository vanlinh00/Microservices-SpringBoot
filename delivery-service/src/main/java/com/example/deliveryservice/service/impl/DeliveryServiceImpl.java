package com.example.deliveryservice.service.impl;

import com.example.deliveryservice.dto.CompletedDeliveryDTO;
import com.example.deliveryservice.service.DeliveryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
3. Service với Lazy Initialization
 + Chúng ta sẽ sử dụng Spring Boot để quản lý các service
   và áp dụng Lazy Initialization để tối ưu hóa việc khởi tạo bean.
 */

@Lazy
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public List<CompletedDeliveryDTO> getAllCompletedDeliveries() {
        List<CompletedDeliveryDTO> deliveries = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            CompletedDeliveryDTO dto = new CompletedDeliveryDTO(
                    "ladingCode" + i,
                    "postman" + i,
                    "status" + i

            );
            deliveries.add(dto);
        }

        return deliveries;
    }

}
