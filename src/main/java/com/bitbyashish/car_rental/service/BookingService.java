package com.bitbyashish.car_rental.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.dto.BookingRequest;
import com.bitbyashish.car_rental.dto.PaymentRequest;
import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Payment;
import com.bitbyashish.car_rental.entity.User;
import com.bitbyashish.car_rental.enums.BookingStatus;
import com.bitbyashish.car_rental.enums.PaymentMethod;
import com.bitbyashish.car_rental.enums.PaymentStatus;
import com.bitbyashish.car_rental.repository.BookingRepository;
import com.bitbyashish.car_rental.repository.CarVariantRepository;
import com.bitbyashish.car_rental.repository.PaymentRepository;
import com.bitbyashish.car_rental.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CarVariantRepository carVariantRepository;
    private final PaymentRepository paymentRepository;

    public Booking createBooking(Long customerId, Long carVariantId, LocalDateTime start, LocalDateTime end) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        CarVariant car = carVariantRepository.findById(carVariantId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        
        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCarVariant(car);
        booking.setStartDate(start);
        booking.setEndDate(end);
        booking.setStatus(BookingStatus.PENDING);
        
        return bookingRepository.save(booking);
    }

    public Booking approveBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        booking.setStatus(BookingStatus.APPROVED);
        booking.getCarVariant().setAvailable(false);
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Payment processPayment(Long bookingId, PaymentMethod method, double amount) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(UUID.randomUUID().toString());
        
        Payment savedPayment = paymentRepository.save(payment);
        booking.setPayment(savedPayment);
        booking.setStatus(BookingStatus.PAID);
        bookingRepository.save(booking);
        
        return savedPayment;
    }
}