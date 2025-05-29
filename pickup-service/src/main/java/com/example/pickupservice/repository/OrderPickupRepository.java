package com.example.pickupservice.repository;

import com.example.pickupservice.entity.OrderPickup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPickupRepository extends JpaRepository<OrderPickup, Long> {

}
