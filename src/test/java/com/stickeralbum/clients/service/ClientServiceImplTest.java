package com.stickeralbum.clients.service;

import com.stickeralbum.clients.model.dto.AddressDTO;
import com.stickeralbum.clients.model.dto.ClientDTO;
import com.stickeralbum.clients.model.entity.Address;
import com.stickeralbum.clients.model.entity.Client;
import com.stickeralbum.clients.model.mapper.ClientMapper;
import com.stickeralbum.clients.repository.ClientRepository;
import com.stickeralbum.clients.service.Impl.ClientServiceImpl;
import com.stickeralbum.clients.service.Impl.ViaCepClientImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ClientServiceImplTest {
    @InjectMocks
    ClientServiceImpl clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ClientMapper mapper;

    @Mock
    ViaCepClientImpl viaCepClient;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientDTOList;
    private Client client;
    private List<Client> clientList;
    private Address address;
    private AddressDTO addressDTO;
    @BeforeEach
    void setUp() {
        addressDTO = AddressDTO.builder().cep("04851-512").build();
        address = Address.builder().cep("04851-512").build();
        clientDTOList = new ArrayList<>();
        clientList = new ArrayList<>();

        clientDTO = new ClientDTO(1, "Francisco", new Date(), "fo@gmail.com", "123456789", addressDTO);
        clientDTOList.add(clientDTO);

        client = new Client(1, "Francisco", new Date(), "fo@gmail.com", "123456789", address);
        clientList.add(client);
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(mapper.toClientDTO(any())).thenReturn(clientDTO);
        when(mapper.toClient(any())).thenReturn(client);
        when(viaCepClient.findAddressByCep(any())).thenReturn(address);

        ClientDTO createdClient = clientService.create(clientDTO);
        verify(clientRepository).save(client);
        assertNotNull(createdClient);
        assertEquals(createdClient.getId(), 1);

    }

    @Test
    void testGetClientById(){
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(mapper.toClientDTO(any())).thenReturn(clientDTO);

        ClientDTO clientFound = clientService.getClientById(1);
        verify(clientRepository).findById(1);
        assertNotNull(clientFound);
        assertEquals(clientFound.getId(), 1);
    }
}
