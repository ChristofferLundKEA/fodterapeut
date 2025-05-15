package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.InsoleBooking;
import com.example.fodterapeut.repository.InsoleBookingRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController // Brug @RestController så alt returneres som JSON eller tekst (uden view)
public class InsoleBookingController {

    @Autowired
    private InsoleBookingRepository insoleBookingRepository;

    @PostMapping("/bookInsole")
    public String bookInsole(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone_number");
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            LocalTime startTime = LocalTime.parse(request.getParameter("timeSlot"));

            // Tjek at datoen er tirsdag eller torsdag
            if (!(date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.THURSDAY)) {
                return "Dato skal være tirsdag eller torsdag";
            }

            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = start.plusHours(2);

            InsoleBooking booking = new InsoleBooking();
            booking.setName(name);
            booking.setEmail(email);
            booking.setPhone_number(phone);
            booking.setDate(date);
            booking.setStart(start);
            booking.setEndTime(end);

            insoleBookingRepository.save(booking);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fejl under booking";
        }
    }
}
