package com.example.TrainBookingSystem.entity;

import com.example.TrainBookingSystem.enums.MealType;
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
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String passengerName;
    @Enumerated(EnumType.STRING)
    private MealType mealPreference; // Added meal preference field
    //private double totalPrice;
    private double totalPrice;
    private double price; // Dynamically calculated price


}

