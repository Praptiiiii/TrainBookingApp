package com.example.TrainBookingSystem.entity;

import com.example.TrainBookingSystem.enums.BerthType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isBooked;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private BerthType berthType; // Updated to use the enum

    private double basePrice;
    private double dynamicPrice;


    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

}
