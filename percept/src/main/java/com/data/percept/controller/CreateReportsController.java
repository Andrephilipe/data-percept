package com.data.percept.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.funtions.geraarquivoexel.ExcelServiceBoleto;
import com.data.percept.funtions.geraarquivoexel.ReportsCarnet;
import com.data.percept.funtions.geraboleto.GeradorDeBoletos;

@RestController
@Validated
public class CreateReportsController {

    private static final String ACCOUNT_CREATE = "Report created";
    public static final Logger logger = LoggerFactory.getLogger(CreateReportsController.class);

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

    @GetMapping("/gerarRelatorio")
    public ResponseEntity<String> createRelatorio(@PathVariable @RequestParam String type, @RequestParam String data) {

        logger.info("createRelatorio: start");
        
        try {
            
            ExcelServiceBoleto.criarArquivoExcel(type, data);

        } catch (Exception e) {

            logger.info("createRelatorio: erro", e);
            return ResponseEntity.internalServerError().body("createRelatorio not created");
        }
        logger.info("createRelatorio: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }

    @GetMapping("/gerarRelatorioCarnet")
    public ResponseEntity<String> createRelatorioCarnet(@PathVariable @RequestParam String type, @RequestParam String data) {

        logger.info("createRelatorio: start");

        try {

            ReportsCarnet.criarArquivoExcel(type, data);

        } catch (Exception e) {

            logger.info("createRelatorio: erro", e);
            return ResponseEntity.internalServerError().body("createRelatorio not created");
        }
        logger.info("createRelatorio: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }
}
