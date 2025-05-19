package com.example.fodterapeut.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int client_id;

    private String name;
    private String email;
    private String phone_number;
    private String cpr;
    private String allergies;
    private boolean insurance;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ClientVisit> visits;
}
