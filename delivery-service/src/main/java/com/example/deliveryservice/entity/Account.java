package com.example.deliveryservice.entity;

;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version // Optimistic Locking
    private Integer version;

    @Column(nullable = false)
    private double balance;

}
