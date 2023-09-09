package com.data.percept.controller;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.dto.PaymentsRequestDTO;
import com.data.percept.models.BankAccount;
import com.data.percept.models.RemessaDebito;
import com.data.percept.models.RemessaPix;
import com.data.percept.repository.BankAccountRepository;
import com.data.percept.repository.PaymentsDebitoRepository;
import com.data.percept.repository.PaymentsPixRepository;

@Validated
@RestController
@RequestMapping("/downPayments")
public class DownPaymentsController {

    public static Logger logger = LoggerFactory.getLogger(DownPaymentsController.class);
    private static final String ODERCREATE = "write-off of payment received";

    @Autowired
    PaymentsPixRepository paymentsPixRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    PaymentsDebitoRepository paymentsDebitoRepository;

    @PostMapping("/pix")
    public ResponseEntity<String> createPaymentsPix(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPayments pix: start");

        try {

            Optional<RemessaPix> paymentPix = paymentsPixRepository.findById(requestPayment.getId());
            if (paymentPix.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "pix"))) {

                    RemessaPix updateRemessaPix = new RemessaPix();
                    updateRemessaPix = paymentPix.get();
                    updateRemessaPix.setStatuPix("pago");
                    updateRemessaPix.setDataPagmento(updateRemessaPix.getDataCriacao());
                    paymentsPixRepository.save(updateRemessaPix);

                    logger.info("createPayments return: etrue!");
                    incrementBalance(requestPayment.getValor());
                } else {
                    return ResponseEntity.internalServerError().body("createPayments pix payment.");
                }

            } else {
                return ResponseEntity.internalServerError().body("createPayments pix not exist");
            }

        } catch (Exception e) {
            logger.info("createPayments pix: erro", e);
            return ResponseEntity.internalServerError().body("createPayments pix not created");
        }
        logger.info("createPayments pix : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

    @PostMapping("/debito")
    public ResponseEntity<String> createPaymentsDebito(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPayments debito: start");

        try {

            Optional<RemessaDebito> paymentDebito = paymentsDebitoRepository.findById(requestPayment.getId());
            if (paymentDebito.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "debito"))) {

                    RemessaDebito updateRemessaDebito = new RemessaDebito();
                    updateRemessaDebito = paymentDebito.get();
                    updateRemessaDebito.setStatusDebito("pago");
                    updateRemessaDebito.setDataCriacao(updateRemessaDebito.getDataCriacao());
                    paymentsDebitoRepository.save(updateRemessaDebito);

                    logger.info("createPayments return: etrue!");
                    incrementBalance(requestPayment.getValor());
                } else {
                    return ResponseEntity.internalServerError().body("createPayments pix payment.");
                }

            } else {
                return ResponseEntity.internalServerError().body("createPayments pix not exist");
            }

        } catch (Exception e) {
            logger.info("createPayments pix: erro", e);
            return ResponseEntity.internalServerError().body("createPayments pix not created");
        }
        logger.info("createPayments pix : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

    public Boolean incrementBalance(BigDecimal valor) {

        logger.info("DownPaymentsController : incrementBalance: isPresent");

        Optional<BankAccount> bankBalance = bankAccountRepository.findById((long) 1);
        BankAccount resultsBank = bankBalance.get();
        BigDecimal currentBalance = resultsBank.getSaldo();

        BigDecimal updatedBalance = currentBalance.add(valor);

        resultsBank.setSaldo(updatedBalance);

        bankAccountRepository.save(resultsBank);
        logger.info("DownPaymentsController : incrementBalance: isPresent" + resultsBank.getSaldo());

        return false;

    }

    public Boolean checkPayment(Long id, String type) {

        logger.info("DownPaymentsController :  checkPayment: start" + id);

        switch (type) {
            case "pix":
                Optional<RemessaPix> buscaStatus = paymentsPixRepository.findById(id);
                RemessaPix checkStatusPix = new RemessaPix();
                checkStatusPix = buscaStatus.get();

                if (buscaStatus.isPresent() && checkStatusPix.getStatusPix().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: pix pago.");
                    return false;

                }
                break;
            case "debito":
                Optional<RemessaDebito> buscaStatusDebito = paymentsDebitoRepository.findById(id);
                RemessaDebito checkStatusDebito = new RemessaDebito();
                checkStatusDebito = buscaStatusDebito.get();

                if (buscaStatusDebito.isPresent() && checkStatusDebito.getStatusDebito().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: pix pago.");
                    return false;

                }
                break;
            default:
                break;
        }

        return true;

    }

}
