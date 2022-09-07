package com.example.examencrud.services;

import com.example.examencrud.dto.ResponseDTO;
import com.example.examencrud.models.ClientModel;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    ResponseEntity<ResponseDTO> getAllClients();

    ResponseEntity<ResponseDTO> getClient(Integer id);

    ResponseEntity<ResponseDTO> createClient(ClientModel client);

    ResponseEntity<ResponseDTO> updateClient(ClientModel client);

    ResponseEntity<ResponseDTO> deleteClient(Integer id);

}
