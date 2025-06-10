package com.bitbyashish.car_rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbyashish.car_rental.dto.BookingDatesRequest;
import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{customerId}/{carVariantId}")
    public ResponseEntity<Booking> createBooking(
            @PathVariable Long customerId,
            @PathVariable Long carVariantId,
            @RequestBody BookingDatesRequest dates) {
        
        Booking booking = bookingService.createBooking(
            customerId, 
            carVariantId, 
            dates.getStartDate(), 
            dates.getEndDate()
        );
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{bookingId}/approve")
    public ResponseEntity<Booking> approveBooking(
            @PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.approveBooking(bookingId));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(
            @PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }
}