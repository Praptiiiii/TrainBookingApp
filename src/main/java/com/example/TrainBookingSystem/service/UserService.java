package com.example.TrainBookingSystem.service;

import com.example.TrainBookingSystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * Retrieves a user based on the specified user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object associated with the provided user ID.
     */
    User getUserById(String userId);
}
