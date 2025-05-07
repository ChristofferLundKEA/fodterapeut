package com.example.fodterapeut.model;

import jakarta.persistence.*;

@Entity
public class CalenderEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;

    private String title;
    private String start;
    private Boolean allDay;
    private String extendedProps;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "insole_id")
    private InsoleBooking insoleBooking;
}
