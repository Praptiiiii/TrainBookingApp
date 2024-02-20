package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.controller.PriceController;
import com.example.TrainBookingSystem.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getDynamicPricesSuccess() {
        String expectedPrices = "Dynamic prices"; // Assume this is the expected result

        when(bookingService.calculateDynamicPrices()).thenReturn(expectedPrices);

        ResponseEntity<String> response = priceController.getDynamicPrices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrices, response.getBody());
        verify(bookingService, times(1)).calculateDynamicPrices();
    }

    @Test
    void getDynamicPricesNoData() {
        // Mocking the service to return null or empty string
        when(bookingService.calculateDynamicPrices()).thenReturn(null);

        ResponseEntity<String> response = priceController.getDynamicPrices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody()); // Assuming null is returned for no data
        verify(bookingService, times(1)).calculateDynamicPrices();
    }


    // Write your test cases here
}

