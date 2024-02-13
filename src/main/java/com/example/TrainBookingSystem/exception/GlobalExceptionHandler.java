package com.example.TrainBookingSystem.exception;
import com.example.TrainBookingSystem.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.rmi.AccessException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//    }

    // Add more exception handlers as needed for your application

    // Handle generic exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
//        ex.printStackTrace();
//        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
//                .type(getCurrentEndpoint())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .error(ex.getMessage())
//                .date(new Date())
//                .build();
//        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @ExceptionHandler({ValidationFailureException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleExceptions(Exception ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .type(getCurrentEndpoint())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .date(new Date())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JWTExpirationException.class, ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> handleJWTExpired(Exception ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .type(getCurrentEndpoint())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(ex.getMessage())
                .date(new Date())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(Exception ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .type(getCurrentEndpoint())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied. You do not have permission to access this resource.")
                .date(new Date())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.FORBIDDEN);
    }

    // Method to get the current endpoint
    private String getCurrentEndpoint() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getRequestURL().toString();
    }


//    private String getCurrentEndpoint() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String endpoint = request.getRequestURL().toString();
//        return endpoint;
//    }
}

