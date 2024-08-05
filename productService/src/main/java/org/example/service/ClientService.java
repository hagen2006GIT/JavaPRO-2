package org.example.service;

import org.example.dto.ClientDto;
import org.example.exception.ClientException;
import org.example.mapper.ClientMapper;
import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto save(ClientDto clientDto) {
        return clientMapper.entityToDto(
                clientRepository.save(
                        clientMapper.dtoToEntity(clientDto)
                )
        );
    }

    public List<ClientDto> findAll() {
        return clientMapper.entityListToDtoList(clientRepository.findAll());
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ClientException("Client with id: " + id + " not found", HttpStatus.NOT_FOUND)
                );
        return clientMapper.entityToDto(client);
    }

    public Client findByIdCl(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ClientException("Client with id: " + id + " not found", HttpStatus.NOT_FOUND)
                );
    }

}