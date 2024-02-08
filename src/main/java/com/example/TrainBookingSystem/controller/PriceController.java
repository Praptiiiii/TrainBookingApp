package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class PriceController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dynamic")
    public ResponseEntity<String> getDynamicPrices() {

        String prices = bookingService.calculateDynamicPrices();
        return ResponseEntity.ok(prices);
    }
}

