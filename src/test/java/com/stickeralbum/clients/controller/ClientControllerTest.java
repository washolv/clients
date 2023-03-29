package com.stickeralbum.clients.controller;

import com.stickeralbum.clients.model.dto.AddressDTO;
import com.stickeralbum.clients.model.dto.ClientDTO;
import com.stickeralbum.clients.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
class ClientControllerTest {
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientService clientService;

    private ClientDTO clientDTO;
    private List<ClientDTO> clientDTOList;

    @BeforeEach
    void setUp() {
        AddressDTO addressDTO = AddressDTO.builder()
                .cep("04851-512")
                .build();
        clientDTO = new ClientDTO(1, "Francisco", new Date(), "fo@gmail.com", "123456789", addressDTO);
        clientDTOList = new ArrayList<>();
        clientDTOList.add(clientDTO);
    }

    @Test
    void testCreateClient() {
        when(clientService.create(clientDTO)).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.createClient(clientDTO);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDTO, response.getBody());
    }

    @Test
    void testGetAllClients() {
        when(clientService.getAllClients()).thenReturn(clientDTOList);

        ResponseEntity<List<ClientDTO>> response = clientController.getAllClients();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDTOList, response.getBody());
    }

    @Test
    void testGetClient() {
        when(clientService.getClientById(clientDTO.getId())).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.getClient(clientDTO.getId());

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDTO, response.getBody());
    }

    @Test
    void testGetClientNotFound() {
        when(clientService.getClientById(clientDTO.getId())).thenReturn(null);

        ResponseEntity<ClientDTO> response = clientController.getClient(clientDTO.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteClient() {
        ResponseEntity<String> response = clientController.deleteClient(clientDTO.getId());

        verify(clientService, times(1)).delete(clientDTO.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente deletado com sucesso", response.getBody());
    }

    @Test
    void testResponseStatusExceptionBadRequest() {
        doThrow(new RuntimeException()).when(clientService).create(clientDTO);

        try {
            clientController.createClient(clientDTO);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        }
    }
}
