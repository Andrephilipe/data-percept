package com.data.percept.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.data.percept.models.InfoResultsGOV;

public class GeraArquivo {
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public void guardaArquivo(List<InfoResultsGOV> resultsList) throws IOException {
        FileWriter escrita = new FileWriter("enderecos.txt");
        escrita.write(gson.toJson(resultsList));
        escrita.close();
    }
}

