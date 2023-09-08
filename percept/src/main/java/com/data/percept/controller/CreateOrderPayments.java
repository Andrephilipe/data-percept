package com.data.percept.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.dto.RemessaPixDTO;
import com.data.percept.models.RemessaDebito;
import com.data.percept.models.RemessaPix;
import com.data.percept.repository.PaymentsDebitoRepository;
import com.data.percept.repository.PaymentsPixRepository;

@Validated
@RestController
@RequestMapping("/createOrderPayments")
public class CreateOrderPayments {

    private static final String ODERCREATE = "order pix created";
    private static final String ODERDELETED = "order pix deleted";

    public static Logger logger = LoggerFactory.getLogger(CreateOrderPayments.class);

    @Autowired
    PaymentsPixRepository paymentsPixRepository;

    @Autowired
    PaymentsDebitoRepository paymentsDebitoRepository;

    @PostMapping("/pix")
    public ResponseEntity<String> createOrder(@Valid @RequestBody RemessaPixDTO remessaPix) {

        logger.info("createOrder pix: start");

        try {

            RemessaPix remessaPix2 = new RemessaPix();
            remessaPix2.setCpf(remessaPix.getCpf());
            remessaPix2.setDataCriacao(remessaPix2.getDataCriacao());
            remessaPix2.setDataValidade(remessaPix2.getDataValidade());
            remessaPix2.setNomeTitular(remessaPix.getNomeTitular());
            remessaPix2.setValor(remessaPix.getValor());
            remessaPix2.setStringPix(remessaPix.getStringPix());
            remessaPix2.setStatuPix("solicitado");
            paymentsPixRepository.save(remessaPix2);

        } catch (Exception e) {
            logger.info("createOrder pix: erro", e);
            return ResponseEntity.internalServerError().body("createOrder pix not created");
        }
        logger.info("createOrder pix : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

    @DeleteMapping("/pix/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {

            Optional<RemessaPix> remessaPix = paymentsPixRepository.findById(id);
            if (remessaPix.isPresent()) {
                paymentsPixRepository.delete(remessaPix.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("createOrder pix not exist");
            }

        } catch (Exception e) {
            logger.info("createOrder pix : erro " + e);
        }

        logger.info("createOrder pix : deleted");
        return ResponseEntity.ok().body(ODERDELETED);
    }

    @PostMapping("/debito")
    public ResponseEntity<String> createOrderDebito(@Valid @RequestBody RemessaPixDTO remessaDebito) {

        logger.info("createOrderDebito debito: start");

        try {

            RemessaDebito remessaDebitoUpadate = new RemessaDebito();
            remessaDebitoUpadate.setCpf(remessaDebito.getCpf());
            remessaDebitoUpadate.setDataCriacao(remessaDebitoUpadate.getDataCriacao());
            remessaDebitoUpadate.setDataValidade(remessaDebitoUpadate.getDataValidade());
            remessaDebitoUpadate.setNomeTitular(remessaDebito.getNomeTitular());
            remessaDebitoUpadate.setValor(remessaDebito.getValor());
            remessaDebitoUpadate.setStatusDebito("solicitado");
            paymentsDebitoRepository.save(remessaDebitoUpadate);

        } catch (Exception e) {
            logger.info("createOrderDebito debito: erro", e);
            return ResponseEntity.internalServerError().body("createOrderDebito debito not created");
        }
        logger.info("createOrderDebito debito : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

}
