package com.data.percept.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.interfaces.CepService;
import com.data.percept.models.Endereco;
import com.data.percept.repository.CepRepository;

@RestController
public class CepRestController {

	@Autowired
	private CepService cepService;

	@Autowired
    private CepRepository _cepRepository;

	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> getCep(@PathVariable String cep) throws IOException, InterruptedException {
		cep = "53441625";
		Endereco endereco = cepService.buscaEnderecoPorCep(cep);
		
		return endereco != null ? ResponseEntity.ok().body(endereco) : ResponseEntity.notFound().build(); 
	}

    @RequestMapping(value = "/cep", method =  RequestMethod.POST)
    public Endereco Post(@Validated @RequestBody Endereco endereco)
    {
        return _cepRepository.save(endereco);
    }

}
