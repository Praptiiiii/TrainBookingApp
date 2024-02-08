package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.dto.SeatDTO;
import com.example.TrainBookingSystem.entity.Booking;
import com.example.TrainBookingSystem.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/book")
    public ResponseEntity<BookingResponseDTO> bookTicket(@RequestBody BookingDTO bookingDTO) {
        BookingResponseDTO responseDTO = bookingService.bookTicket(bookingDTO);
        if (responseDTO == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(responseDTO);
    }

//    @GetMapping("/seat/{bookingId}")
//    public ResponseEntity<SeatDTO> getSeatInformation(@PathVariable Long bookingId) {
//        SeatDTO seatDTO = bookingService.getSeatInformation(bookingId);
//        if (seatDTO == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(seatDTO);
//    }

}
