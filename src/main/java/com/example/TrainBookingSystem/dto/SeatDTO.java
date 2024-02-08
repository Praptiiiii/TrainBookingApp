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
    private BerthType berthType; // Use the enum here
    private double basePrice;
    private double dynamicPrice;
    private boolean isBooked;
}
