package com.data.percept.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.models.RemessaBoleto;
import com.data.percept.repository.CreateRemessaBoletoRepository;

@RestController
@Validated
public class CreateRemessaBoleto {

    private static final String ACCOUNT_CREATE = "remessa created";
    public static Logger logger = LoggerFactory.getLogger(CreateRemessaBoleto.class);

    @Autowired
    CreateRemessaBoletoRepository createRemessaBoletoRepository;

    @PostMapping("/createRemessa")
    public ResponseEntity<String> createRemessa(@Valid @RequestBody RemessaBoleto remessaBoleto) {
        RemessaBoleto newRemessa = new RemessaBoleto();
        logger.info("CreateRemessaBoleto: start");
        try {
            remessaBoleto.setDataCriacao(newRemessa.getDataCriacao());
            remessaBoleto.setDataVencimento(newRemessa.getDataVencimento());
            createRemessaBoletoRepository.save(remessaBoleto);

        } catch (Exception e) {

            logger.info("CreateRemessaBoleto: erro", e);
            return ResponseEntity.internalServerError().body("CreateRemessaBoleto not created");
        }
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }
}
