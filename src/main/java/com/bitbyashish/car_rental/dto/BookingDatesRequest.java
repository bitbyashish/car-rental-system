package com.bitbyashish.car_rental.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDatesRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
