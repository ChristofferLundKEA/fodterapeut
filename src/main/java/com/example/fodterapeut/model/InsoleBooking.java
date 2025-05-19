package com.example.fodterapeut.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
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
