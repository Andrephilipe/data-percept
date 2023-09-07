package com.data.percept.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import com.data.percept.funtions.geraboleto.PdfController;
import com.data.percept.funtions.geraboleto.UsuarioDAO;
import com.data.percept.models.RemessaBoleto;
import com.data.percept.repository.CreateRemessaBoletoRepository;

@RestController
@Validated
public class CreateRemessaBoletoController {

    private static final String ACCOUNT_CREATE = "remessa created";
    public static Logger logger = LoggerFactory.getLogger(CreateRemessaBoletoController.class);

    @Autowired
    CreateRemessaBoletoRepository createRemessaBoletoRepository;

    @PostMapping("/createRemessa")
    public ResponseEntity<String> createRemessa(@Valid @RequestBody RemessaBoleto remessaBoleto) {
        RemessaBoleto newRemessa = new RemessaBoleto();
        logger.info("CreateRemessaBoleto: start");
        try {
            logger.info("CreateRemessaBoleto: inicio do try");
            remessaBoleto.setDataCriacao(newRemessa.getDataCriacao());
            remessaBoleto.setDataVencimento(newRemessa.getDataVencimento());
            remessaBoleto.setDataValidade(newRemessa.getDataValidade());
            remessaBoleto.setStatusBoleto("criado");
            createRemessaBoletoRepository.save(remessaBoleto);

        } catch (Exception e) {

            logger.info("CreateRemessaBoleto: erro", e);
            return ResponseEntity.internalServerError().body("CreateRemessaBoleto not created");
        }
        logger.info("CreateRemessaBoleto: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }

    @GetMapping("/geraBoleto/{id}")
    public ResponseEntity<String> createBoleto(@PathVariable Long id) {

        logger.info("createBoleto: start");
        try {
            Optional<RemessaBoleto> remessaBoleto = createRemessaBoletoRepository.findById(id);
            logger.info("createBoleto: remessaBoleto" + remessaBoleto);

            RemessaBoleto remessaRecebida = remessaBoleto.get();
            UsuarioDAO.inserirUsuario("criado");
            //PdfController.generatePdf(remessaRecebida);
            
        } catch (Exception e) {

            logger.info("createBoleto: erro", e);
            return ResponseEntity.internalServerError().body("createBoleto not created");
        }
        logger.info("createBoleto: end");
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }
}
