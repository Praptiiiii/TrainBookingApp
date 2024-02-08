package com.example.TrainBookingSystem.service;

import com.example.TrainBookingSystem.dto.TrainCoachesDTO;
import com.example.TrainBookingSystem.dto.TrainDTO;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Train;

import java.util.List;

public interface TrainService {

    Train saveTrain(TrainDTO trainDTO);
    List<TrainDTO> getAllTrains();

    // List<Coach> getCoachesByTrainName(String trainName);

    TrainCoachesDTO getCoachesByTrainName(String trainName);
}
