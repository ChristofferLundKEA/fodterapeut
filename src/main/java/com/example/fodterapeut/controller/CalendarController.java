package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.CalenderEvent;
import com.example.fodterapeut.repository.CalenderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CalendarController {

    @Autowired
    private CalenderEventRepository calenderEventRepository;

    @GetMapping("/getAllEvents")
    public List<CalenderEvent> getAllEvents(){
        return calenderEventRepository.findAll();
    }
}
