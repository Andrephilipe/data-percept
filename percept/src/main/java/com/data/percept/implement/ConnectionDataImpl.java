package com.data.percept.implement;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.data.percept.models.Endereco;
import com.data.percept.models.InfoResultsGOV;
import com.data.percept.models.InfoResultsGOVList;
import com.google.gson.Gson;

public class ConnectionDataImpl {

    public Endereco conectaApi(URI url) throws IOException, InterruptedException {

        URI endereco = url;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        HttpResponse<String> response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return new Gson().fromJson(response.body(), Endereco.class);
    }

    public InfoResultsGOVList conectaApiGog(URI url) throws IOException, InterruptedException {

        URI endereco = url;

        HttpClient httpClient = HttpClient.newHttpClient();

        // Criar uma solicitação HTTP com o cabeçalho personalizado
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .headers("chave-api-dados", "6f0e7816664f915e9338acb1e08e4ff3")
                .GET()
                .build();

        // Enviar a solicitação e receber a resposta
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Resposta: " + response.body());

            Gson gson = new Gson();

            InfoResultsGOV[] infoResultsArray = gson.fromJson(response.body(), InfoResultsGOV[].class);
            InfoResultsGOVList list = new InfoResultsGOVList();
            list.setInfoResults(Arrays.asList(infoResultsArray));

            List<InfoResultsGOV> resultsList = list.getInfoResults();

            System.out.println("results list");

            for (InfoResultsGOV result : resultsList) {
                System.out.println("ID: " + result.getId());
                System.out.println("Data Mes Competencia: " + result.getDataMesCompetencia());
                System.out.println("Data Mes Referencia: " + result.getDataMesReferencia());
                System.out.println("------------------------------");

                System.out.println("results final");
                System.out.println(result.getId());
                
            }
            return list;

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
        return null;

    }

}