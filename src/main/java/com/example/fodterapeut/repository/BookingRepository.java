package com.example.fodterapeut.repository;

import com.example.fodterapeut.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
