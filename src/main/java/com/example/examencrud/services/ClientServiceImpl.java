package com.example.examencrud.services;

import com.example.examencrud.dao.FileDAO;
import com.example.examencrud.dto.ResponseDTO;
import com.example.examencrud.models.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    FileDAO fileDAO;

    @Override
    public ResponseEntity<ResponseDTO> getAllClients() {
        try {
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: Clients obtained", fileDAO.readFile()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Server Error " + e.getMessage(), new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getClient(Integer id) {
        try {
            List<ClientModel> clients = fileDAO.readFile();

            if(clients.size() > 0) {
                ClientModel client = clients.stream()
                        .filter(clientModel -> clientModel.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                clients = new ArrayList<ClientModel>();
                if(client != null) {
                    clients.add(client);
                    return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: Client obtained", clients), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: No client obtained", clients), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: No clients in server ", clients), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Server Error " + e.getMessage(), new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> createClient(ClientModel client) {
        try {
            List<ClientModel> clients = fileDAO.readFile();
            int index = clients.size();

            if(client == null) {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: No body found in request", new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            client.setId(clients.size() + 1);
            clients.add(client);

            fileDAO.writeFile(clients);
            clients = new ArrayList<ClientModel>();
            clients.add(client);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: Client created", clients), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Server Error " + e.getMessage(), new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateClient(ClientModel client) {
        try {

            if(client.getId() == null || client.getId() <= 0) {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Id cannot be null", new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<ClientModel> clients = fileDAO.readFile();
            if(clients.size() > 0 && clients.stream().anyMatch(clientModel -> clientModel.getId().equals(client.getId()))) {
                clients.removeIf(clientModel -> clientModel.getId().equals(client.getId()));
                clients.add(client);
                fileDAO.writeFile(clients);
                clients = new ArrayList<ClientModel>();
                clients.add(client);
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: Client updated", clients), HttpStatus.OK);
            } else {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: No clients in server ", clients), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Server Error " + e.getMessage(), new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteClient(Integer id) {
        try {
            if(id <= 0) {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Id cannot be null", new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<ClientModel> clients = fileDAO.readFile();
            if(clients.size() > 0 && clients.stream().anyMatch(clientModel -> clientModel.getId().equals(id))) {
                ClientModel client = clients.stream()
                        .filter(clientModel -> clientModel.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                clients.removeIf(clientModel -> clientModel.getId().equals(id));
                fileDAO.writeFile(clients);
                clients = new ArrayList<ClientModel>();
                clients.add(client);
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("SUCCESS: Client deleted", clients), HttpStatus.OK);
            } else {
                return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: No clients in server ", clients), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("ERROR: Server Error " + e.getMessage(), new ArrayList<ClientModel>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
