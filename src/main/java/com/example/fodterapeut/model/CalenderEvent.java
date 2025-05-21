package com.example.fodterapeut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "end_time")
    private LocalDateTime end;
    private Boolean allDay = false;
    private String description;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "insole_id")
    @JsonIgnore
    private InsoleBooking insoleBooking;
}
