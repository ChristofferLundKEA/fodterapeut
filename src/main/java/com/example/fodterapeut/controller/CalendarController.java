package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.CalenderEvent;
import com.example.fodterapeut.repository.CalenderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/api/events/{id}")
    public ResponseEntity<CalenderEvent> updateEvent(@PathVariable int id, @RequestBody CalenderEvent updated) {
        return calenderEventRepository.findById(id)
                .map(event -> {
                    event.setTitle(updated.getTitle());
                    event.setStart(updated.getStart());
                    event.setEnd(updated.getEnd());
                    event.setDescription(updated.getDescription());
                    return ResponseEntity.ok(calenderEventRepository.save(event));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        if (calenderEventRepository.existsById(id)) {
            calenderEventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
