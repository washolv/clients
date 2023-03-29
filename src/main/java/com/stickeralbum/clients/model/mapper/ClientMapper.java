package com.stickeralbum.clients.model.mapper;
import com.stickeralbum.clients.model.dto.ClientDTO;
import com.stickeralbum.clients.model.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toClientDTO(Client client);
    Client toClient(ClientDTO clientDTO);
    List<ClientDTO> toClientDTOList(List<Client> clients);
    List<Client> toClientList(List<ClientDTO> clients);
}
