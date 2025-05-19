package com.example.fodterapeut.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class CalenderEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;

    private String title;
    private LocalDateTime start;
    private LocalDateTime endTime;
    private Boolean allDay;
    private String description;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "insole_id")
    private InsoleBooking insoleBooking;
}
