package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.Request;
import com.example.fodterapeut.repository.RequestRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class RequestController {

    final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("/getAllRequests")
    public ResponseEntity<List<Request>> getAllRequests(){
        List<Request> allRequests = requestRepository.findAll();
        return ResponseEntity.ok(allRequests);
    }
}
