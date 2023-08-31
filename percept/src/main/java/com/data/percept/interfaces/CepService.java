package com.data.percept.interfaces;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.data.percept.models.Endereco;
import com.data.percept.models.InfoResultsGOVList;

public interface CepService {
	
	@GetMapping("{cep}/json")
    Endereco buscaEnderecoPorCep(@PathVariable("cep") String cep) throws IOException, InterruptedException;
	
    @GetMapping("")
    InfoResultsGOVList buscaInfoResultsGOV(@PathVariable("codeibge") String codeibge,@PathVariable("mesAno") String mesAno) throws IOException, InterruptedException;

}