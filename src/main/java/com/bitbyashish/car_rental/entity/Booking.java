package com.bitbyashish.car_rental.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private CarVariant carVariant;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; // "PENDING", "PAID", "COMPLETED", "CANCELLED"

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}