package com.example.TrainBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {

    private String type;
    private Integer status;
    private String error;
    private Date date;
}
