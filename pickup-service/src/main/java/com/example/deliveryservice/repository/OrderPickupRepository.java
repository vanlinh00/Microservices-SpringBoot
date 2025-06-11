package com.example.deliveryservice.repository;

import com.example.deliveryservice.entity.OrderPickup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPickupRepository extends JpaRepository<OrderPickup, Long> {

}
