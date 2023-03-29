package com.stickeralbum.clients.service;

import com.stickeralbum.clients.model.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    ClientDTO create(ClientDTO client);
    void delete(Integer client);
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Integer id);
}
