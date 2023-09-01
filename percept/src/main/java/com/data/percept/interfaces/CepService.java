package com.data.percept.interfaces;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.data.percept.models.Endereco;

public interface CepService {
	
	@GetMapping("{cep}/json")
    Endereco buscaEnderecoPorCep(@PathVariable("cep") String cep) throws IOException, InterruptedException;
	
}