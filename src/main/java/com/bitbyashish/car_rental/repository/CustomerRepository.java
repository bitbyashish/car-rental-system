package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}