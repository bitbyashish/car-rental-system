package com.bitbyashish.car_rental.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private CarVariant carVariant;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String status; // PENDING, APPROVED, COMPLETED, CANCELLED

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
    // other booking fields
}