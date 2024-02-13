package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.dto.TrainCoachesDTO;
import com.example.TrainBookingSystem.dto.TrainDTO;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/trains")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save train", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Train> saveTrain(@RequestBody TrainDTO trainDTO) {
        Train savedTrain = trainService.saveTrain(trainDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrain);
    }

    @GetMapping("/trains")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all trains", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<TrainDTO>> getAllTrains() {
        List<TrainDTO> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    @GetMapping("/trains/{trainName}/coaches")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get Coaches By Train Name", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<TrainCoachesDTO> getCoachesByTrainName(@PathVariable String trainName) {
        TrainCoachesDTO trainCoachesDTO = trainService.getCoachesByTrainName(trainName);
        return ResponseEntity.ok(trainCoachesDTO);
    }
}
