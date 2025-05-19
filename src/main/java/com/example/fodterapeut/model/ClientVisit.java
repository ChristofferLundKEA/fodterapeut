package com.example.fodterapeut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ClientVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visit_id;

    private LocalDate date;
    private String notes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
