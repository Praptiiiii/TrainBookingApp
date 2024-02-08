package com.example.TrainBookingSystem.repository;


import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findAvailableCoachesByTrainId(Long trainId);
    List<Coach> findByTrainId(Long trainId);

}


