package com.example.TrainBookingSystem.service;

import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.dto.SeatDTO;
import com.example.TrainBookingSystem.entity.Booking;

public interface BookingService {
//    BookingDTO bookTicket(BookingDTO bookingDTO);
//    String calculateDynamicPrices();
//
//    SeatDTO getSeatInformation(Long bookingId);


    BookingResponseDTO bookTicket(BookingDTO bookingDTO);
    //SeatDTO getSeatInformation(Long bookingId);
    String calculateDynamicPrices();
}


