package com.stickeralbum.clients.controller;

import com.stickeralbum.clients.model.dto.ClientDTO;
import com.stickeralbum.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        try{
            return ResponseEntity.ok(clientService.create(client));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar cliente");
        }
    }
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        try{
            return ResponseEntity.ok(clientService.getAllClients());
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro  ao buscar clientes");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Integer id){
        try{
            ClientDTO clientDTO =  clientService.getClientById(id);
            if(clientDTO != null){
                return ResponseEntity.ok(clientService.getClientById(id));
            }
            return ResponseEntity.noContent().build();
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar cliente");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Integer id){
        try{
            clientService.delete(id);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao deletar cliente");
        }
    }
}
