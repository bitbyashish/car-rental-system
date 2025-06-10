package com.bitbyashish.car_rental.service;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.dto.BookingRequest;
import com.bitbyashish.car_rental.dto.PaymentRequest;
import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Payment;
import com.bitbyashish.car_rental.entity.User;
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
    private final CarVariantRepository carVariantRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public Booking createBooking(Long customerId, BookingRequest request) {
        // Find customer or throw exception
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        // Find available car or throw exception
        CarVariant carVariant = carVariantRepository.findById(request.getCarVariantId())
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + request.getCarVariantId()));

        if (!carVariant.isAvailable()) {
            throw new RuntimeException("Car is not available for booking");
        }

        // Create and save booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCarVariant(carVariant);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    public Payment processPayment(Long bookingId, PaymentRequest request) {
        // Find booking or throw exception
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        if (!"PENDING".equals(booking.getStatus())) {
            throw new RuntimeException("Booking is not in payable state");
        }

        // Calculate total amount
        double totalAmount = calculateTotalAmount(booking);

        // Create and save payment
        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus("COMPLETED");
        Payment savedPayment = paymentRepository.save(payment);

        // Update booking
        booking.setPayment(savedPayment);
        booking.setStatus("PAID");
        bookingRepository.save(booking);

        return savedPayment;
    }

    private double calculateTotalAmount(Booking booking) {
        long hours = ChronoUnit.HOURS.between(booking.getStartDate(), booking.getEndDate());
        double hoursToDays = Math.ceil(hours / 24.0); // Round up to full days
        return hoursToDays * booking.getCarVariant().getRentalPrice();
    }

    public Booking bookCar(Long customerId, Long carVariantId, Booking bookingDetails) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CarVariant car = carVariantRepository.findById(carVariantId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        bookingDetails.setCustomer(customer);
        bookingDetails.setCarVariant(car);
        bookingDetails.setStatus("PENDING");

        return bookingRepository.save(bookingDetails);
    }

    public Booking approveBooking(Long bookingId, Long carVariantId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        CarVariant car = carVariantRepository.findById(carVariantId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        booking.setCarVariant(car);
        booking.setStatus("APPROVED");
        car.setAvailable(false);

        carVariantRepository.save(car);
        return bookingRepository.save(booking);
    }
}