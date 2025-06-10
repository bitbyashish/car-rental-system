package com.bitbyashish.car_rental.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long carVariantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
