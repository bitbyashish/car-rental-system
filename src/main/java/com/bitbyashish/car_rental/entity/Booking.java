package com.bitbyashish.car_rental.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.bitbyashish.car_rental.enums.BookingStatus;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    
    @ManyToOne
    @JoinColumn(name = "car_variant_id")
    private CarVariant carVariant;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}

