package com.example.TrainBookingSystem.startegy;

import com.example.TrainBookingSystem.entity.Coach;

public interface PriceCalculationStrategy {
    double calculateDynamicPrice(Coach coach);
}
