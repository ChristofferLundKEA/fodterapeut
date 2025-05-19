package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.Request;
import com.example.fodterapeut.repository.RequestRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/createRequest")
    public ResponseEntity<?> createRequest(@RequestBody Request request){

        try{
            Request newRequest = new Request();

            newRequest.setName(request.getName());
            newRequest.setEmail(request.getEmail());
            newRequest.setPhone_number(request.getPhone_number());
            newRequest.setMessage(request.getMessage());
            newRequest.setIs_seen(false);
            newRequest.setCreated_date(LocalDate.now());

            requestRepository.save(newRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateSeenStatus")
    public ResponseEntity<?> updateSeenStatus(@RequestBody List<Request> updates) {
        for (Request update : updates) {
            requestRepository.findById(update.getRequest_id()).ifPresent(existing -> {
                existing.setIs_seen(update.getIs_seen());
                requestRepository.save(existing);
            });
        }
        return ResponseEntity.ok().build();
    }

}
