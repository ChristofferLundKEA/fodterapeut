package com.example.fodterapeut.repository;

import com.example.fodterapeut.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
