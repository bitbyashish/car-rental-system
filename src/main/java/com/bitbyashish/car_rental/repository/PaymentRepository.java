package com.bitbyashish.car_rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Payment;
import com.bitbyashish.car_rental.enums.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByBooking_Customer_Id(Long userId);
}