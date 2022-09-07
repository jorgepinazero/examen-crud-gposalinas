package com.example.examencrud.controllers;

import com.example.examencrud.dto.ResponseDTO;
import com.example.examencrud.models.ClientModel;
import com.example.examencrud.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("all")
    public ResponseEntity<ResponseDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getClient (@RequestParam Integer id){
        return clientService.getClient(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createClient(@RequestBody ClientModel client) {
        return clientService.createClient(client);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateClient(@RequestBody ClientModel client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteClient(@RequestParam Integer id) {
        return clientService.deleteClient(id);
    }

}
