package com.data.percept.services;

import com.data.percept.implement.ConnectionDataImpl;
import com.data.percept.interfaces.CepService;
import com.data.percept.models.Endereco;

import java.io.IOException;
import java.net.URI;

import org.springframework.stereotype.Service;

@Service
public class CepServiceImpl implements CepService {

    @Override
    public Endereco buscaEnderecoPorCep(String cep) throws IOException, InterruptedException {
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
