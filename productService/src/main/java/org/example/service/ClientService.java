package org.example.service;

import org.example.dto.ClientDto;
import org.example.exception.ClientException;
import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto save(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        clientDto.setId(client.getId());
        clientRepository.save(client);
        return new ClientDto(client.getId(), client.getName());
    }

    public List<ClientDto> findAll() throws ClientException {
        List<ClientDto> users = new ArrayList<>();
        for (Client it : clientRepository.findAll()) {
            users.add(new ClientDto(it.getId(), it.getName()));
        }
        return users;
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ClientException("Client with id: " + id + " not found", HttpStatus.NOT_FOUND)
                );
        return new ClientDto(client.getId(), client.getName());
    }

}