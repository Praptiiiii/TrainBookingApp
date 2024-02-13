package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.dto.SeatDTO;
import com.example.TrainBookingSystem.entity.Booking;
import com.example.TrainBookingSystem.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create a ticket", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BookingResponseDTO> bookTicket(@RequestBody BookingDTO bookingDTO) {
        BookingResponseDTO responseDTO = bookingService.bookTicket(bookingDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
