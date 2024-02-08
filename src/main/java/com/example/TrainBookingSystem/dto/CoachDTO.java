package com.example.TrainBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachDTO {

    private String coachName;
    private int numberOfSeats;
   // private List<SeatDTO> seats;
    private int seatsOccupied;
    private int seatsAvailable;

}