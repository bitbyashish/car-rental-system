package com.bitbyashish.car_rental.entity;

import java.time.LocalDateTime;

import com.bitbyashish.car_rental.enums.PaymentMethod;
import com.bitbyashish.car_rental.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}