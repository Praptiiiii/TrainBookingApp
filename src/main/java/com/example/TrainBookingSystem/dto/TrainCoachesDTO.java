package com.example.TrainBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainCoachesDTO {
    private String trainName;
    private List<CoachDTO> coaches;
    private int totalSeats;
    private int totalSeatsAvailable;
    private int totalSeatsOccupied;
}
