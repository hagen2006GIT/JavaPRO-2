package org.example.controller;

import org.example.dto.ClientDto;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ClientDto addClient(@RequestBody ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @GetMapping("/all")
    public List<ClientDto> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }
}