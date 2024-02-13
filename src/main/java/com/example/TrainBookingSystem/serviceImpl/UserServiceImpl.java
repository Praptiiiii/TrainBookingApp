package com.example.TrainBookingSystem.serviceImpl;


import com.example.TrainBookingSystem.entity.User;
import com.example.TrainBookingSystem.exception.EntityNotFoundException;
import com.example.TrainBookingSystem.repository.UserRepository;
import com.example.TrainBookingSystem.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Implementation of the UserService interface,
 * providing user-related operations.
 */
@Component
public class UserServiceImpl implements UserService {

    /**
     *
     */
    private final UserRepository userRepository;
    /**
     * Constructs a UserServiceImpl with the necessary dependencies.
     *
     * @param userRepo The repository for user data.
     */
    public UserServiceImpl(final UserRepository userRepo) {
        this.userRepository = userRepo;
    }
    /**
     * Load user details by username (email).
     *
     * @param username The username (email) for which to load user details.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException if the user with the
     * specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(
            final String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(
                username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid user details"));
    }

    @Override
    public User getUserById(final String userId) {
        // TODO Auto-generated method stub
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(
                        "User by this " + userId + " doesn't exists"));
    }


}
