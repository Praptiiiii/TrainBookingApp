package com.example.TrainBookingSystem.dto;


import com.example.TrainBookingSystem.enums.BerthType;
import com.example.TrainBookingSystem.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

    private String passengerName;
    private String message;
    private String seatNumber;
    private BerthType berthType;
   // private double basePrice;
    private double ticketPrice;
    private MealType mealType; // Added mealType field
    private String coachName;
    private Date timeDetails;
}
