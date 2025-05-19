package com.example.fodterapeut.repository;

import com.example.fodterapeut.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
