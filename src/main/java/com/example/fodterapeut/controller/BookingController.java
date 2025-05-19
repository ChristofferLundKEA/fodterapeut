package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.Booking;
import com.example.fodterapeut.model.Client;
import com.example.fodterapeut.repository.BookingRepository;
import com.example.fodterapeut.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/admin/book")
    public String createBooking(HttpServletRequest request) {
        String treatment = request.getParameter("treatment");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime startTime = LocalTime.parse(request.getParameter("start"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
        String description = request.getParameter("description");
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        Booking booking = new Booking();
        booking.setTreatment(treatment);
        booking.setDate(date);
        booking.setStart(LocalDateTime.of(date, startTime));
        booking.setEndTime(LocalDateTime.of(date, endTime));
        booking.setDescription(description);
        booking.setClient(client);

        bookingRepository.save(booking);

        return "redirect:/html/dashboard.html";
    }
}
