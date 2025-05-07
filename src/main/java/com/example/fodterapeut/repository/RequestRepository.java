package com.example.fodterapeut.repository;

import com.example.fodterapeut.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
