package com.data.percept.implement;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.data.percept.models.Endereco;
import com.google.gson.Gson;

public class ConnectionDataImpl {

    public Endereco conectaApi(URI url) throws IOException, InterruptedException {

        URI endereco = url;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        HttpResponse<String> response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return new Gson().fromJson(response.body(), Endereco.class);
    }

}