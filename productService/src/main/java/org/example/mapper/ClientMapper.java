package org.example.mapper;

import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    Client dtoToEntity(ClientDto clientDto);

    ClientDto entityToDto(Client client);

    List<ClientDto> entityListToDtoList(List<Client> clients);
}
