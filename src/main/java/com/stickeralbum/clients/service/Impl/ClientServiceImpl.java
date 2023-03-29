package com.stickeralbum.clients.service.Impl;

import com.stickeralbum.clients.model.dto.AddressDTO;
import com.stickeralbum.clients.model.dto.ClientDTO;
import com.stickeralbum.clients.model.entity.Address;
import com.stickeralbum.clients.model.entity.Client;
import com.stickeralbum.clients.model.mapper.ClientMapper;
import com.stickeralbum.clients.repository.ClientRepository;
import com.stickeralbum.clients.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository repository;
    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ViaCepClientImpl viaCepClient;
    @Transactional()
    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        String cep = clientDTO.getAddress().getCep();
        Address address = viaCepClient.findAddressByCep(cep);
        Client client = clientMapper.toClient(clientDTO);
        client.setAddress(address);
        return clientMapper.toClientDTO(repository.save(client));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientMapper.toClientDTOList(repository.findAll());
    }

    @Override
    public ClientDTO getClientById(Integer id) {
        Optional<Client> clientOptional = repository.findById(id);
        if(clientOptional.isPresent()){
            return clientMapper.toClientDTO(clientOptional.get());
        }
        return null;
    }
}
