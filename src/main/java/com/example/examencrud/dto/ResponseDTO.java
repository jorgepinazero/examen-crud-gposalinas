package com.example.examencrud.dto;

import com.example.examencrud.models.ClientModel;

import java.util.List;

public class ResponseDTO {

    private String message;
    private List<ClientModel> items;

    public ResponseDTO(String message, List<ClientModel> items) {
        this.message = message;
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientModel> getItems() {
        return items;
    }

    public void setItems(List<ClientModel> items) {
        this.items = items;
    }
}
