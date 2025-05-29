package com.example.pickupservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_pickups")
@Data
public class OrderPickup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private String pickupStatus;
}
