package com.example.fodterapeut.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ClientVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visit_id;

    private LocalDate date;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
