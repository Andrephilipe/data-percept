package com.data.percept.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.PerceptApplication;
import com.data.percept.interfaces.BuscaAPIGovServices;
import com.data.percept.models.InfoResultsGOV;

@RestController
public class GovRestController {
	
public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);
	
	@Autowired
	private BuscaAPIGovServices buscaService;

	@GetMapping("/gov/{codeibge}/{mesAno}")
	public ResponseEntity<InfoResultsGOV> getInfo(@PathVariable String codeibge, @PathVariable String mesAno)
			throws IOException, InterruptedException {
		logger.info("GovRestController: Inicio");
		try {

			logger.info("GovRestController: Entrando no consultaInfo");
			InfoResultsGOV resultsInfo = buscaService.consultaInfo(codeibge, mesAno);

			logger.info("GovRestController: retornou do consultaInfo");
			return resultsInfo != null ? ResponseEntity.ok().body(resultsInfo) : ResponseEntity.notFound().build();

		} catch (Exception e) {
			logger.info("GovRestController: Erro" + e);

			return ResponseEntity.internalServerError().build();
		}

	}

}
