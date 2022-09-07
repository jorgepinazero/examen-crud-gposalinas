package com.example.examencrud.dao;

import com.example.examencrud.models.ClientModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileDAO {

    public List<ClientModel> readFile() {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("clients.json"));
            List<ClientModel> clients = gson.fromJson(reader, new TypeToken<List<ClientModel>>() {}.getType());

            if(clients == null)
                clients = new ArrayList<ClientModel>();

            return clients;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ClientModel>();
        }
    }

    public void writeFile(List<ClientModel> clients) {
        try (Writer writer = new FileWriter("clients.json")) {
            if(clients != null) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(clients, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
