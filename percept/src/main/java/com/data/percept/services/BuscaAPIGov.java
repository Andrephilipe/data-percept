package com.data.percept.services;

import java.io.IOException;
import java.net.URI;

import com.data.percept.implement.ConnectionDataImpl;
import com.data.percept.models.InfoResultsGOVList;

/**
 * BuscaAPIGov
 */
public class BuscaAPIGov {

    public InfoResultsGOVList consultaInfo(String codeige, String mesAno)
            throws IOException, InterruptedException {

        try {

            URI endereco = URI.create("https://api.portaldatransparencia.gov.br/api-de-dados/auxilio-brasil-sacado-beneficiario-por-municipio?codigoIbge=" + codeige + "&mesAno=" + mesAno +"&pagina=1");

            System.out.println("endereco" + endereco);
            ConnectionDataImpl test = new ConnectionDataImpl();

            InfoResultsGOVList resultsGog = test.conectaApiGog(endereco);
            return resultsGog;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Erro na conexão com o HTTP. consultaInfo");
            System.out.println(e.getMessage());
        }
        return null;
    }
}