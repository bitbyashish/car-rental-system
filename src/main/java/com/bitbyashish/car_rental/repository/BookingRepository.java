package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
