package com.example.fodterapeut.repository;

import com.example.fodterapeut.model.CalenderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderEventRepository extends JpaRepository<CalenderEvent, Integer> {
}
