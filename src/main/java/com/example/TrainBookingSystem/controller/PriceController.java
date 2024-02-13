package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PriceController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dynamic")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Ticket Price", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<String> getDynamicPrices() {

        String prices = bookingService.calculateDynamicPrices();
        return ResponseEntity.ok(prices);
    }
}

