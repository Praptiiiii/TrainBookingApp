package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.controller.AdminController;
import com.example.TrainBookingSystem.dto.TrainCoachesDTO;
import com.example.TrainBookingSystem.dto.TrainDTO;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.service.TrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private TrainService trainService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTrainSuccess() {
        TrainDTO trainDTO = new TrainDTO(); // Assume this is properly set up
        Train savedTrain = new Train(); // Assume this is the expected result

        when(trainService.saveTrain(any(TrainDTO.class))).thenReturn(savedTrain);

        ResponseEntity<Train> response = adminController.saveTrain(trainDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedTrain, response.getBody());
        verify(trainService, times(1)).saveTrain(any(TrainDTO.class));
    }

    @Test
    void getAllTrainsSuccess() {
        List<TrainDTO> trains = Arrays.asList(new TrainDTO(), new TrainDTO()); // Assume these are properly set up
        when(trainService.getAllTrains()).thenReturn(trains);
        ResponseEntity<List<TrainDTO>> response = adminController.getAllTrains();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(trainService, times(1)).getAllTrains();
    }

    @Test
    void getAllTrainsEmpty() {
        when(trainService.getAllTrains()).thenReturn(Arrays.asList());
        ResponseEntity<List<TrainDTO>> response = adminController.getAllTrains();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(trainService, times(1)).getAllTrains();
    }

    @Test
    void getCoachesByTrainNameSuccess() {
        String trainName = "Express";
        TrainCoachesDTO coachesDTO = new TrainCoachesDTO(); // Assume this is properly set up
        when(trainService.getCoachesByTrainName(trainName)).thenReturn(coachesDTO);
        ResponseEntity<TrainCoachesDTO> response = adminController.getCoachesByTrainName(trainName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(coachesDTO, response.getBody());
        verify(trainService, times(1)).getCoachesByTrainName(trainName);
    }



}

