package com.data.percept.implement;

import java.io.IOException;
import java.net.URI;

import com.data.percept.models.Endereco;

public class Buscacep {
        public Endereco buscaEndereco(String cep) throws IOException, InterruptedException {

        try {

            ConnectionDataImpl test = new ConnectionDataImpl();

            URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json");

            Endereco retornoCEP = test.conectaApi(endereco);

            System.out.println("Teste de lei");
            System.out.println(retornoCEP);

            System.out.println("Teste de lei jsons");

            System.out.println(retornoCEP);
            return retornoCEP;
        } catch (Exception e) {
            System.out.println("Erro na conexão com o HTTP.");
            System.out.println(e.getMessage());
        }
        return null;

    }
}
