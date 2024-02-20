package com.example.TrainBookingSystem.serviceImpl;

import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.entity.Booking;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.enums.BerthType;
import com.example.TrainBookingSystem.enums.MealType;
import com.example.TrainBookingSystem.repository.BookingRepository;
import com.example.TrainBookingSystem.repository.CoachRepository;
import com.example.TrainBookingSystem.repository.SeatRepository;
import com.example.TrainBookingSystem.repository.TrainRepository;
import com.example.TrainBookingSystem.serviceImpl.BookingServiceImpl;
import com.example.TrainBookingSystem.startegy.PriceCalculationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CoachRepository coachRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private PriceCalculationStrategy priceCalculationStrategy;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testBookTicket_InvalidPassengerName() {
        BookingDTO bookingDTO = new BookingDTO();
        // Set invalid passenger name (null)
        bookingDTO.setPassengerName(null);
        // Set other valid data
        bookingDTO.setTrainName("Express");
        bookingDTO.setMealPreference(MealType.VEG);
        bookingDTO.setBerthPreference(new BerthType[]{BerthType.LOWER});

        // Ensure IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> bookingService.bookTicket(bookingDTO));
    }

    // Add more test cases for edge cases such as invalid train name, invalid meal preference, no suitable coach available, no suitable seat available, etc.

    @Test
    public void testCalculateDynamicPrices() {
        // Mock data for coaches
        Coach coach1 = new Coach();
        coach1.setCoachName("Coach 1");
        Coach coach2 = new Coach();
        coach2.setCoachName("Coach 2");
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach1);
        coaches.add(coach2);

        // Mock price calculation strategy
        when(priceCalculationStrategy.calculateDynamicPrice(coach1)).thenReturn(1500.0);
        when(priceCalculationStrategy.calculateDynamicPrice(coach2)).thenReturn(2000.0);

        // Mock coach repository
        when(coachRepository.findAll()).thenReturn(coaches);

        // Test calculateDynamicPrices method
        String prices = bookingService.calculateDynamicPrices();
        assertNotNull(prices);
        assertTrue(prices.contains("Coach 1: Rs. 1500.0"));
        assertTrue(prices.contains("Coach 2: Rs. 2000.0"));
    }


    @Test
    void testBookTicket() {
        // Arrange
        BookingRepository bookingRepository = mock(BookingRepository.class);
        CoachRepository coachRepository = mock(CoachRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        PriceCalculationStrategy priceCalculationStrategy = mock(PriceCalculationStrategy.class);
        SeatRepository seatRepository = mock(SeatRepository.class);
        TrainRepository trainRepository = mock(TrainRepository.class);

        BookingServiceImpl bookingServiceImpl = new BookingServiceImpl(
        );

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> bookingServiceImpl.bookTicket(new BookingDTO()));
        // Add more test cases as needed
    }
}
