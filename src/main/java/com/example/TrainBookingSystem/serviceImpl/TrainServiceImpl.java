package com.example.TrainBookingSystem.serviceImpl;

import com.example.TrainBookingSystem.dto.CoachDTO;
import com.example.TrainBookingSystem.dto.TrainCoachesDTO;
import com.example.TrainBookingSystem.dto.TrainDTO;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.enums.BerthType;
import com.example.TrainBookingSystem.repository.CoachRepository;
import com.example.TrainBookingSystem.repository.SeatRepository;
import com.example.TrainBookingSystem.repository.TrainRepository;
import com.example.TrainBookingSystem.service.TrainService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Train saveTrain(TrainDTO trainDTO) {
        Train train = modelMapper.map(trainDTO, Train.class);
        for (int i = 0; i < 10; i++) {
            Coach coach = new Coach();
            coach.setCoachName("Coach " + (i + 1));
            for (int j = 0; j < 20; j++) {
                Seat upperBerth = new Seat();
                upperBerth.setBerthType(BerthType.UPPER); // Use enum value
                upperBerth.setSeatNumber("U" + (j + 1));
                upperBerth.setCoach(coach);
                seatRepository.save(upperBerth);

                Seat lowerBerth = new Seat();
                lowerBerth.setBerthType(BerthType.LOWER); // Use enum value
                lowerBerth.setSeatNumber("L" + (j + 1));
                lowerBerth.setCoach(coach);
                seatRepository.save(lowerBerth);

                Seat middleBerth = new Seat();
                middleBerth.setBerthType(BerthType.MIDDLE); // Use enum value
                middleBerth.setSeatNumber("M" + (j + 1));
                middleBerth.setCoach(coach);
                seatRepository.save(middleBerth);
            }
            coach.setTrain(train);
            coachRepository.save(coach);
        }
        train = trainRepository.save(train);
        return train;
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream()
                .map(train -> modelMapper.map(train, TrainDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TrainCoachesDTO getCoachesByTrainName(String trainName) {
        Train train = trainRepository.findByTrainName(trainName);
        List<Coach> coaches = train.getCoaches();

        int totalSeats = 0;
        int totalSeatsOccupied = 0;
        List<CoachDTO> coachDTOs = new ArrayList<>();

        for (Coach coach : coaches) {
            int numberOfSeats = coach.getSeats().size(); // Total seats in the coach
            int seatsOccupied = 0;

            // Count occupied seats
            for (Seat seat : coach.getSeats()) {
                if (seat.isBooked()) {
                    seatsOccupied++;
                    totalSeatsOccupied++;
                }
            }

            int seatsAvailable = numberOfSeats - seatsOccupied;

            totalSeats += numberOfSeats;

            CoachDTO coachDTO = new CoachDTO();
            coachDTO.setCoachName(coach.getCoachName());
            coachDTO.setNumberOfSeats(numberOfSeats);
            //  coachDTO.setSeats(new ArrayList<>()); // Set seats to null or handle accordingly if needed
            coachDTO.setSeatsOccupied(seatsOccupied);
            coachDTO.setSeatsAvailable(seatsAvailable);
            coachDTOs.add(coachDTO);
        }

        TrainCoachesDTO trainCoachesDTO = new TrainCoachesDTO();
        trainCoachesDTO.setTrainName(trainName);
        trainCoachesDTO.setCoaches(coachDTOs);
        trainCoachesDTO.setTotalSeats(totalSeats);
        trainCoachesDTO.setTotalSeatsOccupied(totalSeatsOccupied);
        trainCoachesDTO.setTotalSeatsAvailable(totalSeats - totalSeatsOccupied);

        return trainCoachesDTO;
    }
}
