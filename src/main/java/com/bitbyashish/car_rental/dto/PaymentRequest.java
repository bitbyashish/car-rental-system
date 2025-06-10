package com.bitbyashish.car_rental.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String paymentMethod; // "CREDIT_CARD", "DEBIT_CARD", etc.
}
