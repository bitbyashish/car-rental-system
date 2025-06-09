package com.bitbyashish.car_rental.service;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.Booking;
import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Customer;
import com.bitbyashish.car_rental.repository.BookingRepository;
import com.bitbyashish.car_rental.repository.CarVariantRepository;
import com.bitbyashish.car_rental.repository.CustomerRepository;
import com.bitbyashish.car_rental.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final CarVariantRepository carVariantRepository;
    private final PaymentRepository paymentRepository;

    public Booking bookCar(Long customerId, Long carVariantId, Booking bookingRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CarVariant carVariant = carVariantRepository.findById(carVariantId)
                .orElseThrow(() -> new RuntimeException("Car variant not found"));

        if (!carVariant.isAvailable()) {
            throw new RuntimeException("Car is not available for booking");
        }

        bookingRequest.setCustomer(customer);
        bookingRequest.setCarVariant(carVariant);
        bookingRequest.setStatus("PENDING");

        return bookingRepository.save(bookingRequest);
    }

    public Booking approveBooking(Long bookingId, Long carVariantId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        CarVariant carVariant = carVariantRepository.findById(carVariantId)
                .orElseThrow(() -> new RuntimeException("Car variant not found"));

        booking.setStatus("APPROVED");
        booking.setCarVariant(carVariant);
        carVariant.setAvailable(false);
        carVariantRepository.save(carVariant);

        return bookingRepository.save(booking);
    }

    // other booking methods
}
