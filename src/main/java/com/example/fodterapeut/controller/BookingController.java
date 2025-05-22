package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.Booking;
import com.example.fodterapeut.model.CalenderEvent;
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
        String clientIdParam = request.getParameter("clientId");
        if (clientIdParam == null || clientIdParam.isBlank() || clientIdParam.equals("undefined")) {
            throw new IllegalArgumentException("Ugyldigt clientId – vælg en klient fra listen");
        }

        int clientId = Integer.parseInt(clientIdParam);


        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        Booking booking = new Booking();
        booking.setTreatment(treatment);
        booking.setDate(date);
        booking.setStart(LocalDateTime.of(date, startTime));
        booking.setEndTime(LocalDateTime.of(date, endTime));
        booking.setDescription(description);
        booking.setClient(client);

        // Opret kalender-event og knyt det til bookingen
        CalenderEvent event = new CalenderEvent();
        event.setTitle(treatment + " - " + client.getName());
        event.setStart(booking.getStart());
        event.setEnd(booking.getEndTime());
        event.setDescription(description);
        event.setAllDay(false);
        event.setBooking(booking); // VIGTIGT: forbind event med booking

        // Tilføj event til bookingens event-liste
        booking.setCalenderEvents(java.util.List.of(event)); // opret ny liste med kun dette ene event

        // Gem både booking og event (CascadeType.ALL)
        bookingRepository.save(booking);

        return "redirect:/html/adminbooking.html";
    }
}
