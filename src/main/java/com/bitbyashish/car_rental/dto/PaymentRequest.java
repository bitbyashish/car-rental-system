package com.bitbyashish.car_rental.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String paymentMethod; // "CREDIT_CARD", "DEBIT_CARD", etc.
    private String cardNumber;       // For card payments
    private String cardExpiry;       // MM/YY format
    private String cvv;              // For card payments
}
