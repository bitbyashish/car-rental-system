package com.bitbyashish.car_rental.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.dto.PaymentRequest;
import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.entity.Payment;
import com.bitbyashish.car_rental.enums.BookingStatus;
import com.bitbyashish.car_rental.enums.PaymentMethod;
import com.bitbyashish.car_rental.enums.PaymentStatus;
import com.bitbyashish.car_rental.repository.BookingRepository;
import com.bitbyashish.car_rental.repository.PaymentRepository;
import com.bitbyashish.car_rental.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public Payment processPayment(Long bookingId, PaymentRequest request) {
        // 1. Find booking (using Long)
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // 2. Validate customer exists (using Long)
        userRepository.findById(booking.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 3. Create payment
        Payment payment = new Payment();
        payment.setAmount(calculateTotalAmount(booking));
        
        // FIX: Convert String to PaymentMethod enum
        payment.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod().toUpperCase()));
        
        payment.setTransactionId(generateTransactionId());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDateTime.now());

        // 4. Link payment to booking
        Payment savedPayment = paymentRepository.save(payment);
        booking.setPayment(savedPayment);
        booking.setStatus(BookingStatus.PAID);
        bookingRepository.save(booking);

        return savedPayment;
    }

    public Payment getPaymentDetails(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getPaymentsByUser(Long userId) {
        return paymentRepository.findByBooking_Customer_Id(userId);
    }

    private double calculateTotalAmount(Booking booking) {
        long hours = ChronoUnit.HOURS.between(booking.getStartDate(), booking.getEndDate());
        double days = Math.ceil(hours / 24.0); // Round up to full days
        return days * booking.getCarVariant().getRentalPrice();
    }

    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}