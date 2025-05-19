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

    public int getInsole_id() {
        return insole_id;
    }

    public void setInsole_id(int insole_id) {
        this.insole_id = insole_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<CalenderEvent> getCalenderEvents() {
        return calenderEvents;
    }

    public void setCalenderEvents(List<CalenderEvent> calenderEvents) {
        this.calenderEvents = calenderEvents;
    }


}
