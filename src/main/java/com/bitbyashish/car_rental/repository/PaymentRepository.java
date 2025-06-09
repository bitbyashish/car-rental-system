package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}