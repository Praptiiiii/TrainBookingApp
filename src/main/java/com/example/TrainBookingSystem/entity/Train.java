package com.example.TrainBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Coach> coaches = new ArrayList<>();

    // Constructors, getters, and setters
}
