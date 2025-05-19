package com.example.fodterapeut.controller;

import com.example.fodterapeut.model.Booking;
import com.example.fodterapeut.model.Client;
import com.example.fodterapeut.model.ClientHistory;
import com.example.fodterapeut.repository.BookingRepository;
import com.example.fodterapeut.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;


    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(updatedClient.getName());
                    client.setEmail(updatedClient.getEmail());
                    client.setPhone_number(updatedClient.getPhone_number());
                    client.setCpr(updatedClient.getCpr());
                    client.setAllergies(updatedClient.getAllergies());
                    client.setInsurance(updatedClient.isInsurance());
                    return ResponseEntity.ok(clientRepository.save(client));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientRepository.save(client));
    }

    // Her gemmes al historik i hukommelsen
    private final Map<Integer, List<ClientHistory>> memoryHistory = new HashMap<>();

    // Her hentes historik for en klient
    @GetMapping("/{id}/history")
    public ResponseEntity<List<ClientHistory>> getHistory(@PathVariable int id) {
        List<ClientHistory> histories = memoryHistory.getOrDefault(id, new ArrayList<>());
        return ResponseEntity.ok(histories);
    }

    // Her tilføjes en ny historiknote
    @PostMapping("/{id}/history")
    public ResponseEntity<ClientHistory> addHistory(@PathVariable int id, @RequestBody ClientHistory history) {
        memoryHistory.computeIfAbsent(id, k -> new ArrayList<>()).add(history);
        return ResponseEntity.ok(history);
    }

}
