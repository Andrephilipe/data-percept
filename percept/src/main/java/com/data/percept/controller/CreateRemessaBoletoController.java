package com.data.percept.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.dto.GeraRelatorioDTO;
import com.data.percept.funtions.geraarquivoexel.ExcelServiceBoleto;
import com.data.percept.funtions.geraboleto.GeradorDeBoletos;
import com.data.percept.repository.CreateRemessaBoletoRepository;

@RestController
@Validated
public class CreateRemessaBoletoController {

    private static final String ACCOUNT_CREATE = "remessa created";
    public static final Logger logger = LoggerFactory.getLogger(CreateRemessaBoletoController.class);

    @Autowired
    CreateRemessaBoletoRepository createRemessaBoletoRepository;

    @GetMapping("/geraBoleto/{status}")
    public ResponseEntity<String> createBoleto(@PathVariable String status) {

        logger.info("createBoleto: start");
        try {
            GeradorDeBoletos.geraBoleto(status);

        } catch (Exception e) {

            logger.info("createBoleto: erro", e);
            return ResponseEntity.internalServerError().body("createBoleto not created");
        }
        logger.info("createBoleto: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }

    @PostMapping("/gerarRelatorio")
    public ResponseEntity<String> createRelatorio(@RequestBody GeraRelatorioDTO geraRelatorioDTO) {

        logger.info("createRelatorio: start");
        try {
            ExcelServiceBoleto.criarArquivoExcel(geraRelatorioDTO.getNomeTipo(), geraRelatorioDTO.getDataInicial(), geraRelatorioDTO.getDataFinal());

        } catch (Exception e) {

            logger.info("createRelatorio: erro", e);
            return ResponseEntity.internalServerError().body("createRelatorio not created");
        }
        logger.info("createRelatorio: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }
}
