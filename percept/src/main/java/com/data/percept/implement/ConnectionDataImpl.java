package com.data.percept.implement;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.percept.PerceptApplication;
import com.data.percept.models.Endereco;
import com.data.percept.models.InfoResultsGOV;
import com.data.percept.models.InfoResultsGOVList;
import com.data.percept.services.ExcelService;
import com.data.percept.services.GeraArquivo;
import com.google.gson.Gson;

public class ConnectionDataImpl {
public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);
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

    public InfoResultsGOV conectaApiGog(URI url) throws IOException, InterruptedException {
        logger.info("conectaApiGog: Inicio.");

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

            logger.info("conectaApiGog: entrando no try.");
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("conectaApiGog: resposta do serviço GOV." + response.body());

            Gson gson = new Gson();

            InfoResultsGOV[] infoResultsArray = gson.fromJson(response.body(), InfoResultsGOV[].class);
            InfoResultsGOVList list = new InfoResultsGOVList();
            List<InfoResultsGOV> arquivoLista = list.getInfoResults();
            list.setInfoResults(Arrays.asList(infoResultsArray));

            List<InfoResultsGOV> resultsList = list.getInfoResults();

            GeraArquivo guardarEnderecos = new GeraArquivo();
            guardarEnderecos.guardaArquivo(arquivoLista);

            System.out.println("results list");
            ExcelService testeExcel = new ExcelService();
            testeExcel.criarArquivoExcel("teste", "gov");
            

            for (InfoResultsGOV result : resultsList) {
                System.out.println("ID: " + result.getId().toString());
                System.out.println("Data Mes Competencia: " + result.getDataMesCompetencia());
                System.out.println("Data Mes Referencia: " + result.getDataMesReferencia());
                System.out.println("------------------------------");

                System.out.println("results final");
                testeExcel.atualizarArquivoExcel(result.getId().toString(), result.getDataMesCompetencia(), result.getDataMesReferencia());              
                
            }
            return null;

        } catch (Exception e) {

            System.err.println("Ocorreu um erro: " + e.getMessage());
            logger.info("conectaApiGog: Erro." + e);
        }
        return null;

    }

}