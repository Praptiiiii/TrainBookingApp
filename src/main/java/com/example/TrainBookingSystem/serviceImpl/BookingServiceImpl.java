package com.example.TrainBookingSystem.serviceImpl;

import com.example.TrainBookingSystem.dto.BookingDTO;
import com.example.TrainBookingSystem.dto.BookingResponseDTO;
import com.example.TrainBookingSystem.dto.SeatDTO;
import com.example.TrainBookingSystem.entity.Booking;
import com.example.TrainBookingSystem.entity.Coach;
import com.example.TrainBookingSystem.entity.Seat;
import com.example.TrainBookingSystem.entity.Train;
import com.example.TrainBookingSystem.enums.BerthType;
import com.example.TrainBookingSystem.repository.BookingRepository;
import com.example.TrainBookingSystem.repository.CoachRepository;
import com.example.TrainBookingSystem.repository.SeatRepository;
import com.example.TrainBookingSystem.repository.TrainRepository;
import com.example.TrainBookingSystem.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    @Transactional
//    public BookingDTO bookTicket(BookingDTO bookingDTO) {
//        // Retrieve the train by its name
//        Train train = trainRepository.findByTrainName(bookingDTO.getTrainName());
//        if (train == null) {
//            // Handle the case when the train is not found
//            return null;
//        }
//
//        // Logic to find available seat based on user preference and coach sequence
//        Coach coach = findAvailableCoach(train.getId(), bookingDTO.getBerthPreference());
//        if (coach == null) {
//            // Handle the case when no suitable coach is available
//            return null;
//        }
//
//        // Find the first available seat in the coach based on user preference
//        Seat seat = findAvailableSeat(coach, bookingDTO.getBerthPreference());
//        if (seat == null) {
//            // Handle the case when no suitable seat is available
//            return null;
//        }
//
//        // Update the seat status to booked
//        seat.setBooked(true);
//        //seat.setBooked(true);
//        seat.setBasePrice(2000); // Set base price
//        seat.setDynamicPrice(calculateDynamicPrice(coach)); // Set dynamically calculated price
//
//        seatRepository.save(seat);
//
//        // Create and save the booking
//        Booking booking = new Booking();
//        booking.setPassengerName(bookingDTO.getPassengerName());
//        booking.setMealPreference(bookingDTO.getMealPreference());
//        booking.setSeat(seat);
//        booking.setPrice(calculateDynamicPrice(coach)); // Set dynamically calculated price
//        booking.setTotalPrice(booking.getPrice()); // Set total price initially as price per seat
//        booking = bookingRepository.save(booking);
//
//        return modelMapper.map(booking, BookingDTO.class);
//    }


    @Override
    @Transactional
    public BookingResponseDTO bookTicket(BookingDTO bookingDTO) {
        // Validate that passengerName is not null or empty
        if (bookingDTO.getPassengerName() == null || bookingDTO.getPassengerName().isEmpty()) {
            throw new IllegalArgumentException("Passenger name must be provided.");
        }

        // Validate that none of the other fields are null or empty
        if (bookingDTO.getTrainName() == null || bookingDTO.getTrainName().isEmpty() ||
                bookingDTO.getBerthPreference() == null || bookingDTO.getBerthPreference().length == 0 ||
                bookingDTO.getMealPreference() == null) {
            throw new IllegalArgumentException("All fields in the booking request must be provided.");
        }

        // Convert passengerName and trainName to uppercase
        String passengerName = bookingDTO.getPassengerName().toUpperCase();
        String trainName = bookingDTO.getTrainName().toUpperCase();

        // Retrieve the train by its name
        Train train = trainRepository.findByTrainName(trainName);
        if (train == null) {
            // Handle the case when the train is not found
            throw new IllegalArgumentException("Train with name '" + trainName + "' not found.");
        }

        // Logic to find available seat based on user preference and coach sequence
        Coach coach = findAvailableCoach(train.getId(), bookingDTO.getBerthPreference());
        if (coach == null) {
            // Handle the case when no suitable coach is available
            throw new IllegalStateException("No suitable coach available for booking.");
        }

        // Find the first available seat in the coach based on user preference
        Seat seat = findAvailableSeat(coach, bookingDTO.getBerthPreference());
        if (seat == null) {
            // Handle the case when no suitable seat is available
            throw new IllegalStateException("No suitable seat available for booking.");
        }

        // Update the seat status to booked
        seat.setBooked(true);
        seat.setBasePrice(2000); // Set base price
        seat.setDynamicPrice(calculateDynamicPrice(coach)); // Set dynamically calculated price

        seatRepository.save(seat);

        // Create and save the booking
        Booking booking = new Booking();
        booking.setPassengerName(passengerName);
        booking.setMealPreference(bookingDTO.getMealPreference());
        booking.setSeat(seat);
        booking.setPrice(calculateDynamicPrice(coach)); // Set dynamically calculated price
        booking.setTotalPrice(booking.getPrice()); // Set total price initially as price per seat
        booking = bookingRepository.save(booking);

        // Prepare the booking response DTO
        BookingResponseDTO response = new BookingResponseDTO();
        response.setMessage("Hi " + booking.getPassengerName()+ ", your seat has been booked successfully!");
        response.setPassengerName(booking.getPassengerName());
        response.setSeatNumber(seat.getSeatNumber()); // Add seat information to the response
        response.setBerthType(seat.getBerthType()); // Set berth type directly from the enum
        response.setCoachName(seat.getCoach().getCoachName());
        response.setMealType(booking.getMealPreference());
        response.setTicketPrice(seat.getDynamicPrice());

        return response;
    }




//    @Override
//    public SeatDTO getSeatInformation(Long bookingId) {
//        // Retrieve booking details from the database using the bookingId
//        Booking booking = bookingRepository.findById(bookingId).orElse(null);
//        if (booking == null) {
//            // Handle the case when the booking is not found
//            return null;
//        }
//
//        // Map the seat entity to SeatDTO
//        SeatDTO seatDTO = modelMapper.map(booking.getSeat(), SeatDTO.class);
//        // You can perform any additional processing or mapping here if needed
//
//        return seatDTO;
//    }


    @Override
    public String calculateDynamicPrices() {
        List<Coach> coaches = coachRepository.findAll();
        StringBuilder pricesBuilder = new StringBuilder();

        for (Coach coach : coaches) {
            double price = calculateDynamicPrice(coach);
            pricesBuilder.append("Coach ").append(coach.getCoachName()).append(": Rs. ").append(price).append("\n");
        }

        return pricesBuilder.toString();
    }

    private double calculateDynamicPrice(Coach coach) {
        int totalSeats = coach.getSeats().size();
        long bookedSeats = coach.getSeats().stream().filter(Seat::isBooked).count();
        double occupancyPercentage = ((double) bookedSeats / totalSeats) * 100;

        double basePrice = 2000; // Base price for every seat
        double dynamicPrice = basePrice;

        // Apply dynamic pricing rules based on occupancy percentage
        if (occupancyPercentage >= 40) {
            dynamicPrice += basePrice * 0.18; // 18% increase
        } else if (occupancyPercentage >= 35) {
            dynamicPrice += basePrice * 0.15; // 15% increase
        } else if (occupancyPercentage >= 30) {
            dynamicPrice += basePrice * 0.12; // 12% increase
        } else if (occupancyPercentage >= 20) {
            dynamicPrice += basePrice * 0.10; // 10% increase
        }

        return dynamicPrice;
    }

    private Coach findAvailableCoach(Long trainId, BerthType[] berthPreference) {
        List<Coach> availableCoaches = coachRepository.findAvailableCoachesByTrainId(trainId);
        List<Coach> sequenceOneCoaches = new ArrayList<>();
        List<Coach> sequenceTwoCoaches = new ArrayList<>();

        // Separate coaches into two sequences
        for (Coach coach : availableCoaches) {
            int coachNumber = Integer.parseInt(coach.getCoachName().split(" ")[1]);
            if (coachNumber % 2 != 0) {
                sequenceOneCoaches.add(coach);
            } else {
                sequenceTwoCoaches.add(coach);
            }
        }

        // Iterate through the sequence one coaches first
        for (Coach coach : sequenceOneCoaches) {
            if (isBerthAvailable(coach, berthPreference)) {
                return coach;
            }
        }

        // If all sequence one coaches are filled, move to sequence two coaches
        for (Coach coach : sequenceTwoCoaches) {
            if (isBerthAvailable(coach, berthPreference)) {
                return coach;
            }
        }

        return null;
    }

    private boolean isBerthAvailable(Coach coach, BerthType[] berthPreference) {
        for (BerthType berthType : berthPreference) {
            for (Seat seat : coach.getSeats()) {
                if (seat.getBerthType().equals(berthType) && !seat.isBooked()) {
                    return true;
                }
            }
        }
        return false;
    }

    private Seat findAvailableSeat(Coach coach, BerthType[] berthPreference) {
        for (BerthType berthType : berthPreference) {
            for (Seat seat : coach.getSeats()) {
                if (seat.getBerthType().equals(berthType) && !seat.isBooked()) {
                    return seat;
                }
            }
        }
        return null;
    }

}
