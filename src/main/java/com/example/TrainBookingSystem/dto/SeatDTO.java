package com.example.TrainBookingSystem.dto;

import com.example.TrainBookingSystem.enums.BerthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    private String seatNumber;
    private BerthType berthType;
    private double basePrice;
    private double dynamicPrice;
    private boolean isBooked;
}
