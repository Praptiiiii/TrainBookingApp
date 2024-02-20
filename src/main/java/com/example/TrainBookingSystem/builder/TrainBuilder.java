package com.example.TrainBookingSystem.builder;

import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.enums.BerthType;

import java.util.ArrayList;
import java.util.List;

public class TrainBuilder {

    public static Train buildTrain(String trainName) {
        Train train = new Train();
        train.setTrainName(trainName);
        List<Coach> coaches = buildCoaches(train);
        train.setCoaches(coaches);
        return train;
    }

    private static List<Coach> buildCoaches(Train train) {
        List<Coach> coaches = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Coach coach = buildCoach(train, "Coach " + (i + 1));
            coaches.add(coach);
        }
        return coaches;
    }

    private static Coach buildCoach(Train train, String coachName) {
        Coach coach = new Coach();
        coach.setCoachName(coachName);
        coach.setTrain(train);
        List<Seat> seats = buildSeats(coach);
        coach.setSeats(seats);
        return coach;
    }

    private static List<Seat> buildSeats(Coach coach) {
        List<Seat> seats = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            seats.add(buildSeat(coach, BerthType.UPPER, "U" + (j + 1)));
            seats.add(buildSeat(coach, BerthType.LOWER, "L" + (j + 1)));
            seats.add(buildSeat(coach, BerthType.MIDDLE, "M" + (j + 1)));
        }
        return seats;
    }

    private static Seat buildSeat(Coach coach, BerthType berthType, String seatNumber) {
        Seat seat = new Seat();
        seat.setBerthType(berthType);
        seat.setSeatNumber(seatNumber);
        seat.setCoach(coach);
        return seat;
    }
}

