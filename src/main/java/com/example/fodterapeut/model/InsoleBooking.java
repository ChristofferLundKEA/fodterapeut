package com.example.fodterapeut.model;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private String cpr;
    private LocalDate date;
    private LocalTime time;

    @OneToMany(mappedBy = "insoleBooking", cascade = CascadeType.ALL)
    private List<CalenderEvent> calenderEvents;
}
