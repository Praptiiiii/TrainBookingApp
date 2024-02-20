package com.example.TrainBookingSystem.startegy;

import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import org.springframework.stereotype.Component;

@Component
public class DefaultPriceCalculationStrategy implements PriceCalculationStrategy {
    @Override
    public double calculateDynamicPrice(Coach coach) {
        int totalSeats = coach.getSeats().size();
        long bookedSeats = coach.getSeats().stream().filter(Seat::isBooked).count();
        double occupancyPercentage = ((double) bookedSeats / totalSeats) * 100;

        double basePrice = 2000; // Base price for every seat
        double dynamicPrice = basePrice;

        // Apply dynamic pricing rules based on occupancy percentage
        if (occupancyPercentage >= 40) {
            dynamicPrice += basePrice * 0.18; // 18% increase
        } else if (occupancyPercentage >= 35) {
            dynamicPrice += basePrice * 0.15; // 15% increase
        } else if (occupancyPercentage >= 30) {
            dynamicPrice += basePrice * 0.12; // 12% increase
        } else if (occupancyPercentage >= 20) {
            dynamicPrice += basePrice * 0.10; // 10% increase
        }

        return dynamicPrice;
    }
}

