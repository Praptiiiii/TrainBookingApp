package com.example.TrainBookingSystem.repository;

import com.example.TrainBookingSystem.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Train findByTrainName(String trainName);

}
