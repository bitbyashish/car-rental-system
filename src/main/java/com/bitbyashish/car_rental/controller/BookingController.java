package com.bitbyashish.car_rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{customerId}/{carVariantId}")
    public ResponseEntity<Booking> bookCar(
            @PathVariable Long customerId,
            @PathVariable Long carVariantId,
            @RequestBody Booking bookingRequest) {
        return ResponseEntity.ok(bookingService.bookCar(customerId, carVariantId, bookingRequest));
    }

    @PutMapping("/approve/{bookingId}/{carVariantId}")
    public ResponseEntity<Booking> approveBooking(
            @PathVariable Long bookingId,
            @PathVariable Long carVariantId) {
        return ResponseEntity.ok(bookingService.approveBooking(bookingId, carVariantId));
    }
}