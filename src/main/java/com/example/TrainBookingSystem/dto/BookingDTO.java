package com.example.TrainBookingSystem.dto;

import com.example.TrainBookingSystem.enums.BerthType;
import com.example.TrainBookingSystem.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private String passengerName;
    private String trainName;
    private BerthType[] berthPreference;
    private MealType mealPreference;




}
