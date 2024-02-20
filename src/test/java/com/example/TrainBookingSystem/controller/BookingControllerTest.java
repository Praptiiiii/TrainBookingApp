package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.controller.BookingController;
import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void bookTicketSuccess() {
        BookingDTO bookingDTO = new BookingDTO(); // Assume this is properly set up
        BookingResponseDTO responseDTO = new BookingResponseDTO(); // Assume this is the expected result

        when(bookingService.bookTicket(bookingDTO)).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> response = bookingController.bookTicket(bookingDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(bookingService, times(1)).bookTicket(bookingDTO);
    }




    // Write your test cases here
}

