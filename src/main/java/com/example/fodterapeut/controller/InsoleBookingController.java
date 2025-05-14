package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.InsoleBooking;
import com.example.fodterapeut.repository.InsoleBookingRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class InsoleBookingController {

    @Autowired
    private InsoleBookingRepository insoleBookingRepository;

    @PostMapping("/insole-booking")
    public String bookInsole(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone_number");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime startTime = LocalTime.parse(request.getParameter("timeSlot"));

        // tjekker at det er tirsdag eller torsdag
        if (!(date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.THURSDAY)) {
            throw new IllegalArgumentException("Datoen skal være en tirsdag eller torsdag");
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

        return "redirect:/bekræftelse.html";
    }
}
