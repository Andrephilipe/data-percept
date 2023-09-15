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

import com.data.percept.dto.OrdersDTO;
import com.data.percept.funtions.geraboleto.CalculateBoletoInstallments;
import com.data.percept.models.OrderPaymentsBoleto;
import com.data.percept.models.OrderPaymentsCarnet;
import com.data.percept.models.OrderPaymentsDebito;
import com.data.percept.models.OrderPaymentsPix;
import com.data.percept.repository.PaymentsBoletoRepository;
import com.data.percept.repository.PaymentsCarnetRepository;
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
    private static final String ODERCARNET = "Payment carnet created";
    private static final String CARNETDELETED = "Payment carnet deleted";
    private static final String ERRO_INTERNO = "Error internal";

    public static final Logger logger = LoggerFactory.getLogger(CreateOrderPayments.class);

    @Autowired
    PaymentsPixRepository paymentsPixRepository;

    @Autowired
    PaymentsDebitoRepository paymentsDebitoRepository;

    @Autowired
    PaymentsBoletoRepository paymentsBoletoRepository;

    @Autowired
    PaymentsCarnetRepository paymentsCarnetRepository;

    @PostMapping("/pix")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrdersDTO orderPaymentsPix) {

        logger.info("createOrder pix: start");

        try {

            OrderPaymentsPix orderPixCreated = new OrderPaymentsPix();
            orderPixCreated.setCpf(orderPaymentsPix.getCpf());
            orderPixCreated.setDataCriacao(orderPixCreated.getDataCriacao());
            orderPixCreated.setDataValidade(orderPixCreated.getDataValidade());
            orderPixCreated.setNomeTitular(orderPaymentsPix.getNomeTitular());
            orderPixCreated.setValor(orderPaymentsPix.getValor());
            orderPixCreated.setStringPix(orderPaymentsPix.getStringPix());
            orderPixCreated.setStatuPix(STATUS_CRIACAO_REMESSA);
            paymentsPixRepository.save(orderPixCreated);

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

            Optional<OrderPaymentsPix> remessaPix = paymentsPixRepository.findById(id);
            if (remessaPix.isPresent()) {
                paymentsPixRepository.delete(remessaPix.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deleteOrder pix not exist");
            }

        } catch (Exception e) {
            logger.error("deleteOrder pix : erro ", e);
            return ResponseEntity.internalServerError().body(ERRO_INTERNO);

        }

        logger.info("createOrder pix : deleted");
        return ResponseEntity.ok().body(ODERDELETED);
    }

    @PostMapping("/debito")
    public ResponseEntity<String> createOrderDebito(@Valid @RequestBody OrdersDTO orderPaymentDebito) {

        logger.info("createOrderDebito debito: start");

        try {

            OrderPaymentsDebito orderDebitoCreated = new OrderPaymentsDebito();
            orderDebitoCreated.setCpf(orderPaymentDebito.getCpf());
            orderDebitoCreated.setDataCriacao(orderDebitoCreated.getDataCriacao());
            orderDebitoCreated.setDataValidade(orderDebitoCreated.getDataValidade());
            orderDebitoCreated.setNomeTitular(orderPaymentDebito.getNomeTitular());
            orderDebitoCreated.setValor(orderPaymentDebito.getValor());
            orderDebitoCreated.setStatusDebito(STATUS_CRIACAO_REMESSA);
            paymentsDebitoRepository.save(orderDebitoCreated);

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

            Optional<OrderPaymentsDebito> remessaDebito = paymentsDebitoRepository.findById(id);
            if (remessaDebito.isPresent()) {
                paymentsDebitoRepository.delete(remessaDebito.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deletePaymentDebito debito not exist");
            }

        } catch (Exception e) {
            logger.error("deletePaymentDebito debito : erro ", e);
            return ResponseEntity.internalServerError().body(ERRO_INTERNO);
        }

        logger.info("deletePaymentDebito debito : deleted");
        return ResponseEntity.ok().body(DEBITODELETED);
    }

    @PostMapping("/boleto")
    public ResponseEntity<String> createOrderBoleto(@Valid @RequestBody OrdersDTO orderPaymentBoleto) {

        logger.info("createOrderBoleto : start");

        try {

            OrderPaymentsBoleto orderBoletoCreated = new OrderPaymentsBoleto();
            orderBoletoCreated.setCpf(orderPaymentBoleto.getCpf());
            orderBoletoCreated.setDataCriacao(orderBoletoCreated.getDataCriacao());
            orderBoletoCreated.setDataValidade(orderBoletoCreated.getDataValidade());
            orderBoletoCreated.setDataVencimento(orderBoletoCreated.getDataVencimento());
            orderBoletoCreated.setNomeTitular(orderPaymentBoleto.getNomeTitular());
            orderBoletoCreated.setValor(orderPaymentBoleto.getValor());
            orderBoletoCreated.setStatusBoleto(STATUS_CRIACAO_REMESSA);
            orderBoletoCreated.setMunicipio(orderPaymentBoleto.getMunicipio());

            paymentsBoletoRepository.save(orderBoletoCreated);

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

            Optional<OrderPaymentsDebito> remessaDebito = paymentsDebitoRepository.findById(id);
            if (remessaDebito.isPresent()) {
                paymentsDebitoRepository.delete(remessaDebito.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deletePaymentBoleto not exist");
            }

        } catch (Exception e) {
            logger.error("deletePaymentBoleto : erro ", e);
            return ResponseEntity.internalServerError().body(ERRO_INTERNO);
        }

        logger.info("deletePaymentDebito debito : deleted");
        return ResponseEntity.ok().body(BOLETODELETED);
    }

    @PostMapping("/carnet")
    public ResponseEntity<String> createOrderCarnet(@Valid @RequestBody OrdersDTO orderPaymentCarnet) {

        logger.info("createOrderCarnet : start");

        try {

            OrderPaymentsCarnet odersBoletoCreated = new OrderPaymentsCarnet();
            odersBoletoCreated.setCpf(orderPaymentCarnet.getCpf());
            odersBoletoCreated.setDataCriacao(odersBoletoCreated.getDataCriacao());
            odersBoletoCreated.setDataValidade(odersBoletoCreated.getDataValidade());
            odersBoletoCreated.setDataVencimento(odersBoletoCreated.getDataVencimento());
            odersBoletoCreated.setNomeTitular(orderPaymentCarnet.getNomeTitular());
            odersBoletoCreated.setValor(orderPaymentCarnet.getValor());
            odersBoletoCreated.setStatusCarnet(STATUS_CRIACAO_REMESSA);
            odersBoletoCreated.setMunicipio(orderPaymentCarnet.getMunicipio());
            odersBoletoCreated.setParcelas(orderPaymentCarnet.getParcelas());
            odersBoletoCreated.setParcelasRestantes(orderPaymentCarnet.getParcelas());
            
            if (Boolean.TRUE.equals(CalculateBoletoInstallments.verificaValor(orderPaymentCarnet.getValor()))) {
                BigDecimal valorAtual = CalculateBoletoInstallments.calculaValor(orderPaymentCarnet.getValor(), orderPaymentCarnet.getParcelas());
                odersBoletoCreated.setValorParcelas(valorAtual);
                BigDecimal quantidadeParcelasBigdecimal = new BigDecimal(orderPaymentCarnet.getParcelas());
                odersBoletoCreated.setSaldoDevedor(valorAtual.multiply(quantidadeParcelasBigdecimal));

            }

            paymentsCarnetRepository.save(odersBoletoCreated);

        } catch (Exception e) {
            logger.info("createOrderCarnet : erro", e);
            return ResponseEntity.internalServerError().body("createOrderCarnet not created");
        }
        logger.info("createOrderCarnet : end");
        return ResponseEntity.ok().body(ODERCARNET);
    }

    @DeleteMapping("/carnet/{id}")
    public ResponseEntity<String> deletePaymentCarnet(@PathVariable Long id) {
        try {

            Optional<OrderPaymentsCarnet> remessaCarnet = paymentsCarnetRepository.findById(id);
            if (remessaCarnet.isPresent()) {
                paymentsCarnetRepository.delete(remessaCarnet.get());
            } else {
                return ((BodyBuilder) ResponseEntity.noContent()).body("deletePaymentCarnet not exist");
            }

        } catch (Exception e) {
            logger.error("deletePaymentCarnet : erro ", e);
            return ResponseEntity.internalServerError().body(ERRO_INTERNO);
        }

        logger.info("deletePaymentCarnet : deleted");
        return ResponseEntity.ok().body(CARNETDELETED);
    }

}
