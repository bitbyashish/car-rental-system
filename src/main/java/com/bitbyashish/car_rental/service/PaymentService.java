package com.bitbyashish.car_rental.service;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.entity.Payment;
import com.bitbyashish.car_rental.repository.BookingRepository;
import com.bitbyashish.car_rental.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public Payment processPayment(Long bookingId, Payment payment) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        payment.setPaymentConfirmed(true);
        Payment savedPayment = paymentRepository.save(payment);

        booking.setPayment(savedPayment);
        bookingRepository.save(booking);

        return savedPayment;
    }
}