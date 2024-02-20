package com.example.TrainBookingSystem.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.TrainBookingSystem.dto.TrainDTO;
import com.example.TrainBookingSystem.dto.TrainCoachesDTO;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.repository.CoachRepository;
import com.example.TrainBookingSystem.repository.TrainRepository;
import com.example.TrainBookingSystem.serviceImpl.TrainServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private TrainServiceImpl trainService;



    @Test
    void getAllTrains_NoTrains_ReturnsEmptyList() {
        // Arrange
        when(trainRepository.findAll()).thenReturn(List.of());

        // Act
        List<TrainDTO> trains = trainService.getAllTrains();

        // Assert
        assertNotNull(trains);
        assertTrue(trains.isEmpty());
        verify(trainRepository, times(1)).findAll();
    }



}
