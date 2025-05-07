package com.example.fodterapeut.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int booking_id;

    private String treatment;
    private LocalDate date;
    private LocalTime time;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<CalenderEvent> calenderEvents;
}
