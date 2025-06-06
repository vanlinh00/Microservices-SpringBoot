package com.example.pickupservice.service.impl;

import com.example.pickupservice.dto.CompletedDeliveryDTO;
import com.example.pickupservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



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
