package com.example.deliveryservice.entity;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingNumber;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id") // Khóa ngoại liên kết với Route
    private Route route; // Mỗi đơn hàng chỉ thuộc một tuyến đường
}
