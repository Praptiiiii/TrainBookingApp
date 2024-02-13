package com.example.TrainBookingSystem.controller;

import com.example.TrainBookingSystem.dto.AuthResponseDTO;
import com.example.TrainBookingSystem.dto.LoginRequestDTO;
import com.example.TrainBookingSystem.dto.SignUpRequestDTO;
import com.example.TrainBookingSystem.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthServiceImpl authService;

    /**
     * Endpoint for verifying user tokens.
     *
     * @param verifyRequestDTO The data transfer object containing information needed for user verification.
     * @return ResponseEntity containing the response data after verifying the user.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginRequestDTO verifyRequestDTO) {
        return new ResponseEntity<>(authService.verify(verifyRequestDTO), HttpStatus.OK);
    }
    /**
     * Endpoint for creating new user accounts.
     *
     * @param signUpRequestDTO The data transfer object containing user registration information.
     * @return ResponseEntity containing the response data after creating the user account.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> createUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return new ResponseEntity<>(authService.createUser(signUpRequestDTO), HttpStatus.CREATED);
    }
}
