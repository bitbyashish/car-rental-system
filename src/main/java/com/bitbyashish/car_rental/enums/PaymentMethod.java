package com.bitbyashish.car_rental.enums;

public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    BANK_TRANSFER;

    // Handle case-insensitive input
    public static PaymentMethod fromString(String value) {
        return valueOf(value.toUpperCase());
    }
}