package com.data.percept.controller;

import java.math.BigDecimal;
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

import com.data.percept.dto.RemessaDTO;
import com.data.percept.funtions.geraboleto.CalculateBoletoInstallments;
import com.data.percept.models.RemessaBoleto;
import com.data.percept.models.RemessaDebito;
import com.data.percept.models.RemessaPix;
import com.data.percept.repository.CreateRemessaBoletoRepository;
import com.data.percept.repository.PaymentsDebitoRepository;
import com.data.percept.repository.PaymentsPixRepository;

@Validated
@RestController
@RequestMapping("/createOrderPayments")
public class CreateOrderPayments {

    private static final String ODERCREATE = "Payment pix created";
    private static final String ODERDELETED = "Payment pix deleted";
    private static final String ODERDEBITO = "Payment debito created";
    private static final String DEBITODELETED = "Payment debito deleted";
    private static final String STATUS_CRIACAO_REMESSA = "solicitado";
    private static final String ODERBOLETO = "Payment boleto created";
    private static final String BOLETODELETED = "Payment boleto deleted";

    public static Logger logger = LoggerFactory.getLogger(CreateOrderPayments.class);

    @Autowired
    PaymentsPixRepository paymentsPixRepository;

    @Autowired
    PaymentsDebitoRepository paymentsDebitoRepository;

    @Autowired
    CreateRemessaBoletoRepository createRemessaBoletoRepository;

    @PostMapping("/pix")
    public ResponseEntity<String> createOrder(@Valid @RequestBody RemessaDTO remessaPix) {

        logger.info("createOrder pix: start");

        try {

            RemessaPix remessaPixCreated = new RemessaPix();
            remessaPixCreated.setCpf(remessaPix.getCpf());
            remessaPixCreated.setDataCriacao(remessaPixCreated.getDataCriacao());
            remessaPixCreated.setDataValidade(remessaPixCreated.getDataValidade());
            remessaPixCreated.setNomeTitular(remessaPix.getNomeTitular());
            remessaPixCreated.setValor(remessaPix.getValor());
            remessaPixCreated.setStringPix(remessaPix.getStringPix());
            remessaPixCreated.setStatuPix(STATUS_CRIACAO_REMESSA);
            paymentsPixRepository.save(remessaPixCreated);

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
                return ((BodyBuilder) ResponseEntity.noContent()).body("deleteOrder pix not exist");
            }

        } catch (Exception e) {
            logger.error("deleteOrder pix : erro ", e);
            return ResponseEntity.internalServerError().body("Error internal.");
            
        }

        logger.info("createOrder pix : deleted");
        return ResponseEntity.ok().body(ODERDELETED);
    }

    @PostMapping("/debito")
    public ResponseEntity<String> createOrderDebito(@Valid @RequestBody RemessaDTO remessaDebito) {

        logger.info("createOrderDebito debito: start");

        try {

            RemessaDebito remessaDebitoUpadate = new RemessaDebito();
            remessaDebitoUpadate.setCpf(remessaDebito.getCpf());
            remessaDebitoUpadate.setDataCriacao(remessaDebitoUpadate.getDataCriacao());
            remessaDebitoUpadate.setDataValidade(remessaDebitoUpadate.getDataValidade());
            remessaDebitoUpadate.setNomeTitular(remessaDebito.getNomeTitular());
            remessaDebitoUpadate.setValor(remessaDebito.getValor());
            remessaDebitoUpadate.setStatusDebito(STATUS_CRIACAO_REMESSA);
            paymentsDebitoRepository.save(remessaDebitoUpadate);

        } catch (Exception e) {
            logger.info("createOrderDebito debito: erro", e);
            return ResponseEntity.internalServerError().body("createOrderDebito debito not created");
        }
        logger.info("createOrderDebito debito : end");
        return ResponseEntity.ok().body(ODERDEBITO);
    }

    @DeleteMapping("/debito/{id}")
    public ResponseEntity<String> deletePaymentDebito(@PathVariable Long id) {
        try {

            Optional<RemessaDebito> remessaDebito = paymentsDebitoRepository.findById(id);
            if (remessaDebito.isPresent()) {
                paymentsDebitoRepository.delete(remessaDebito.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deletePaymentDebito debito not exist");
            }

        } catch (Exception e) {
            logger.error("deletePaymentDebito debito : erro ", e);
            return ResponseEntity.internalServerError().body("Error internal.");
        }

        logger.info("deletePaymentDebito debito : deleted");
        return ResponseEntity.ok().body(DEBITODELETED);
    }

    @PostMapping("/boleto")
    public ResponseEntity<String> createOrderBoleto(@Valid @RequestBody RemessaDTO remessaBoleto) {

        logger.info("createOrderBoleto : start");

        try {

            RemessaBoleto remessaBoletoUpadate = new RemessaBoleto();
            remessaBoletoUpadate.setCpf(remessaBoleto.getCpf());
            remessaBoletoUpadate.setDataCriacao(remessaBoletoUpadate.getDataCriacao());
            remessaBoletoUpadate.setDataValidade(remessaBoletoUpadate.getDataValidade());
            remessaBoletoUpadate.setDataVencimento(remessaBoletoUpadate.getDataVencimento());
            remessaBoletoUpadate.setNomeTitular(remessaBoleto.getNomeTitular());
            remessaBoletoUpadate.setValor(remessaBoleto.getValor());
            remessaBoletoUpadate.setStatusBoleto(STATUS_CRIACAO_REMESSA);
            remessaBoletoUpadate.setMunicipio(remessaBoleto.getMunicipio());
            remessaBoletoUpadate.setParcelas(remessaBoleto.getParcelas());
            
            if (Boolean.TRUE.equals(CalculateBoletoInstallments.verificaValor(remessaBoleto.getValor()))) {
                BigDecimal valorAtual = CalculateBoletoInstallments.calculaValor(remessaBoleto.getValor(), remessaBoleto.getParcelas());
                remessaBoletoUpadate.setValorParcelas(valorAtual);

            }

            createRemessaBoletoRepository.save(remessaBoletoUpadate);

        } catch (Exception e) {
            logger.info("createOrderBoleto : erro", e);
            return ResponseEntity.internalServerError().body("createOrderBoleto not created");
        }
        logger.info("createOrderBoleto : end");
        return ResponseEntity.ok().body(ODERBOLETO);
    }

    @DeleteMapping("/boleto/{id}")
    public ResponseEntity<String> deletePaymentBoleto(@PathVariable Long id) {
        try {

            Optional<RemessaDebito> remessaDebito = paymentsDebitoRepository.findById(id);
            if (remessaDebito.isPresent()) {
                paymentsDebitoRepository.delete(remessaDebito.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deletePaymentBoleto not exist");
            }

        } catch (Exception e) {
            logger.error("deletePaymentBoleto : erro ", e);
            return ResponseEntity.internalServerError().body("Error internal.");
        }

        logger.info("deletePaymentDebito debito : deleted");
        return ResponseEntity.ok().body(BOLETODELETED);
    }

}
