package com.bitbyashish.car_rental.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String paymentMethod;
    private String transactionId;
    private String status; // Change from boolean to String

    // Remove setPaymentConfirmed() and use setStatus() instead
    public void markAsCompleted() {
        this.status = "COMPLETED";
    }
}