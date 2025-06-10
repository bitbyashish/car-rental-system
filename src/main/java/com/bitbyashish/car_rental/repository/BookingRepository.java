package com.bitbyashish.car_rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByCarVariantIdAndStatusIn(Long carVariantId, List<BookingStatus> statuses);
}
