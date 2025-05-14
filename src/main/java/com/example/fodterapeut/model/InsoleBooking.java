package com.example.fodterapeut.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class InsoleBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insole_id;

    private String name;
    private String email;
    private String phone_number;
    private LocalDate date;
    private LocalDateTime start;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "insoleBooking", cascade = CascadeType.ALL)
    private List<CalenderEvent> calenderEvents;
}
