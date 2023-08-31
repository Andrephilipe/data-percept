package com.data.percept.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.interfaces.CepService;
import com.data.percept.models.InfoResultsGOVList;

@RestController
public class GovRestController {
	
	@Autowired
	private CepService cepService;

    @GetMapping("/gov/{codeibge}/{mesAno}")
	public ResponseEntity<InfoResultsGOVList> getInfo(@PathVariable String codeibge,@PathVariable String mesAno) throws IOException, InterruptedException {
		try {
			InfoResultsGOVList endereco = cepService.buscaInfoResultsGOV(codeibge, mesAno);
		
			return endereco != null ? ResponseEntity.ok().body(endereco) : ResponseEntity.notFound().build();

		} catch (Exception e) {
			System.out.println("Erro na conexão com o HTTP getInfo.");
            System.out.println(e.getMessage());
			throw e;
		}
		
	}

}
