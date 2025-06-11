package com.example.deliveryservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<Order> orders; // Một tuyến đường có nhiều đơn hàng

    //> Lazy Loading: Danh sách orders chỉ được tải khi gọi getOrders().

}
