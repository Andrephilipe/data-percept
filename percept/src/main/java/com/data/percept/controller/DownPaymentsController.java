package com.data.percept.controller;

import java.math.BigDecimal;
import java.util.Optional;

import jakarta.validation.Valid;

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
import com.data.percept.models.CounterAccount;
import com.data.percept.models.OrderPaymentsBoleto;
import com.data.percept.models.OrderPaymentsCarnet;
import com.data.percept.models.OrderPaymentsCash;
import com.data.percept.models.OrderPaymentsDebito;
import com.data.percept.models.OrderPaymentsPix;
import com.data.percept.repository.BankAccountRepository;
import com.data.percept.repository.CounterAccountRepository;
import com.data.percept.repository.PaymentsBoletoRepository;
import com.data.percept.repository.PaymentsCarnetRepository;
import com.data.percept.repository.PaymentsCashRepository;
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

    @Autowired
    PaymentsCarnetRepository paymentsCarnetRepository;

    @Autowired
    PaymentsBoletoRepository paymentsBoletoRepository;

    @Autowired
    CounterAccountRepository counterAccountRepository;

    @Autowired
    PaymentsCashRepository paymentsCashRepository;

    @PostMapping("/pix")
    public ResponseEntity<String> createPaymentsPix(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPayments pix: start");

        try {

            Optional<OrderPaymentsPix> paymentPix = paymentsPixRepository.findById(requestPayment.getId());
            if (paymentPix.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "pix"))) {

                    OrderPaymentsPix updateRemessaPix = new OrderPaymentsPix();
                    updateRemessaPix = paymentPix.get();
                    updateRemessaPix.setStatuPix("pago");
                    updateRemessaPix.setDataPagmento(updateRemessaPix.getDataCriacao());
                    paymentsPixRepository.save(updateRemessaPix);

                    logger.info("createPayments return: etrue!");
                    incrementBalanceBanck(requestPayment.getValor());
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

            Optional<OrderPaymentsDebito> paymentDebito = paymentsDebitoRepository.findById(requestPayment.getId());
            if (paymentDebito.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "debito"))) {

                    OrderPaymentsDebito updateRemessaDebito = new OrderPaymentsDebito();
                    updateRemessaDebito = paymentDebito.get();
                    updateRemessaDebito.setStatusDebito("pago");
                    updateRemessaDebito.setDataCriacao(updateRemessaDebito.getDataCriacao());
                    paymentsDebitoRepository.save(updateRemessaDebito);

                    logger.info("createPayments return: etrue!");
                    incrementBalanceBanck(requestPayment.getValor());
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

    @PostMapping("/boleto")
    public ResponseEntity<String> createPaymentsBoleto(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPayments boleto: start");
        OrderPaymentsBoleto updateRemessaBoleto = new OrderPaymentsBoleto();

        try {

            Optional<OrderPaymentsBoleto> paymentBoleto = paymentsBoletoRepository.findById(requestPayment.getId());
            if (paymentBoleto.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "boleto"))) {

                    updateRemessaBoleto = paymentBoleto.get();
                    updateRemessaBoleto.setStatusBoleto("pago");
                    updateRemessaBoleto.setDataCriacao(updateRemessaBoleto.getDataCriacao());
                    paymentsBoletoRepository.save(updateRemessaBoleto);

                    logger.info("createPayments return: e true!");
                    incrementBalanceBanck(requestPayment.getValor());
                } else {
                    return ResponseEntity.internalServerError().body("createPayments boleto payment.");
                }

            } else {
                return ResponseEntity.internalServerError().body("createPayments boleto not exist");
            }

        } catch (Exception e) {
            logger.info("createPayments boleto: erro", e);
            return ResponseEntity.internalServerError().body("createPayments boleto not created");
        }
        logger.info("createPayments boleto : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

    @PostMapping("/carnet")
    public ResponseEntity<String> createPaymentsCarnet(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPaymentsCarnet carnet: start");
        OrderPaymentsCarnet updateRemessaBoleto = new OrderPaymentsCarnet();

        try {

            Optional<OrderPaymentsCarnet> paymentBoleto = paymentsCarnetRepository.findById(requestPayment.getId());
            if (paymentBoleto.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "carnet"))) {

                    updateRemessaBoleto = paymentBoleto.get();
                    updateRemessaBoleto.setStatusCarnet("pago");
                    updateRemessaBoleto.setDataCriacao(updateRemessaBoleto.getDataCriacao());
                    paymentsCarnetRepository.save(updateRemessaBoleto);

                    logger.info("createPayments return: e true!");
                    paymentCarnet(requestPayment.getId(), requestPayment.getValor());
                    incrementBalanceCounter(requestPayment.getValor());

                } else {
                    return ResponseEntity.internalServerError().body("createPayments boleto payment.");
                }

            } else {
                return ResponseEntity.internalServerError().body("createPayments boleto not exist");
            }

        } catch (Exception e) {
            logger.info("createPayments boleto: erro", e);
            return ResponseEntity.internalServerError().body("createPayments boleto not created");
        }
        logger.info("createPayments boleto : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

    public Boolean incrementBalanceBanck(BigDecimal valor) {
        try {
            logger.info("DownPaymentsController : incrementBalanceBanck: isPresent");
            Optional<BankAccount> bankBalance = bankAccountRepository.findById((long) 1);
            if (bankBalance.isPresent()) {
                BankAccount resultsBank = bankBalance.get();
                BigDecimal currentBalance = resultsBank.getSaldo();
                BigDecimal updatedBalance = currentBalance.add(valor);
                resultsBank.setSaldo(updatedBalance);
                bankAccountRepository.save(resultsBank);
            }

            logger.info("DownPaymentsController : incrementBalanceBanck: isPresent");

        } catch (Exception e) {
            logger.error("incrementBalanceBanck: erro", e);
            return false;
        }

        return true;

    }

    public Boolean incrementBalanceCounter(BigDecimal valor) {
        try {
            Optional<CounterAccount> bankBalance = counterAccountRepository.findById((long) 1);
            if (bankBalance.isPresent()) {
                CounterAccount resultsBank = bankBalance.get();
                BigDecimal currentBalance = resultsBank.getSaldo();

                BigDecimal updatedBalance = currentBalance.add(valor);

                resultsBank.setSaldo(updatedBalance);

                counterAccountRepository.save(resultsBank);
            }

        } catch (Exception e) {
            logger.error("incrementBalanceCounter: erro", e);
            return false;
        }

        logger.info("DownPaymentsController : incrementBalanceCounter");
        return true;

    }

    public Boolean paymentCarnet(Long id, BigDecimal valor) {

        logger.info("DownPaymentsController : paymentCarnet: start" + id);
        try {

            Optional<OrderPaymentsCarnet> buscaStatusCarnet = paymentsCarnetRepository.findById(id);
            OrderPaymentsCarnet buscaCarnetStatus = new OrderPaymentsCarnet();
            if (buscaStatusCarnet.isPresent()) {
                buscaCarnetStatus = buscaStatusCarnet.get();
                if (buscaCarnetStatus.getParcelas().equals(buscaCarnetStatus.getParcelasRestantes())) {
                    Integer parcelaAtal = buscaCarnetStatus.getParcelas() - 1;
                    BigDecimal valorAtual = buscaCarnetStatus.getValor();
                    BigDecimal resultado = valorAtual.subtract(valor);
                    buscaCarnetStatus.setParcelasRestantes(parcelaAtal);
                    buscaCarnetStatus.setValorDevedor(resultado);
                    if (buscaCarnetStatus.getParcelas().equals(1)) {
                        buscaCarnetStatus.setStatusCarnet("pago");
                    }
                    paymentsCarnetRepository.save(buscaCarnetStatus);
                    return true;
                }
                if (buscaCarnetStatus.getParcelas() >= buscaCarnetStatus.getParcelasRestantes()) {
                    Integer parcelaAtal = buscaCarnetStatus.getParcelasRestantes() - 1;
                    BigDecimal valorAtual = buscaCarnetStatus.getValorDevedor();
                    BigDecimal resultado = valorAtual.subtract(valor);
                    buscaCarnetStatus.setParcelasRestantes(parcelaAtal);
                    buscaCarnetStatus.setValorDevedor(resultado);
                    paymentsCarnetRepository.save(buscaCarnetStatus);
                }

            }
        } catch (Exception e) {
            logger.error("paymentCarnet: erro", e);
            return false;
        }
        return true;
    }

    public Boolean checkPayment(Long id, String type) {

        logger.info("DownPaymentsController :  checkPayment: start" + id);

        switch (type) {
            case "pix":
                Optional<OrderPaymentsPix> buscaStatus = paymentsPixRepository.findById(id);
                OrderPaymentsPix checkStatusPix = new OrderPaymentsPix();
                if (buscaStatus.isPresent())
                    checkStatusPix = buscaStatus.get();
                if (checkStatusPix.getStatusPix().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: pix pago.");
                    return false;
                }
                break;
            case "debito":
                Optional<OrderPaymentsDebito> buscaStatusDebito = paymentsDebitoRepository.findById(id);
                OrderPaymentsDebito checkStatusDebito = new OrderPaymentsDebito();

                if (buscaStatusDebito.isPresent())
                    checkStatusDebito = buscaStatusDebito.get();
                if (checkStatusDebito.getStatusDebito().equalsIgnoreCase("pago")) {
                    logger.info("DownPaymentsController : checkPayment: debito pago.");
                    return false;
                }
                break;

            case "boleto":
                Optional<OrderPaymentsBoleto> buscaStatusBoleto = paymentsBoletoRepository.findById(id);
                OrderPaymentsBoleto checkStatusBoleto = new OrderPaymentsBoleto();
                if (buscaStatusBoleto.isPresent())
                    checkStatusBoleto = buscaStatusBoleto.get();
                if (buscaStatusBoleto.isPresent() && checkStatusBoleto.getStatusBoleto().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: boleto pago.");
                    return false;

                }
                break;

            case "carnet":
                Optional<OrderPaymentsCarnet> buscaStatusCarnet = paymentsCarnetRepository.findById(id);
                OrderPaymentsCarnet buscaCarnetStatus = new OrderPaymentsCarnet();
                if (buscaStatusCarnet.isPresent())
                    buscaCarnetStatus = buscaStatusCarnet.get();
                if (buscaCarnetStatus.getStatusCarnet().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: carnet pago.");
                    return false;

                }
                break;

            case "cash":
                Optional<OrderPaymentsCash> buscaStatuCash = paymentsCashRepository.findById(id);
                OrderPaymentsCash buscaCashtStatus = new OrderPaymentsCash();
                if (buscaStatuCash.isPresent())
                    buscaCashtStatus = buscaStatuCash.get();
                if (buscaCashtStatus.getStatusPagmento().equalsIgnoreCase("pago")) {

                    logger.info("DownPaymentsController : checkPayment: cash pago.");
                    return false;

                }
                break;
            default:
                break;
        }

        return true;

    }

    @PostMapping("/cash")
    public ResponseEntity<String> createPaymentsCash(@Valid @RequestBody PaymentsRequestDTO requestPayment) {

        logger.info("createPayments cash: start");

        try {

            Optional<OrderPaymentsCash> paymentPix = paymentsCashRepository.findById(requestPayment.getId());
            if (paymentPix.isPresent()) {
                Long idBusca = requestPayment.getId();

                if (Boolean.TRUE.equals(checkPayment(idBusca, "cash"))) {

                    OrderPaymentsCash updateRemessaPix = new OrderPaymentsCash();
                    updateRemessaPix = paymentPix.get();
                    updateRemessaPix.setStatusPagmento("pago");
                    updateRemessaPix.setDataPagmento(updateRemessaPix.getDataCriacao());
                    paymentsCashRepository.save(updateRemessaPix);

                    logger.info("createPayments return: etrue!");
                    incrementBalanceCounter(paymentPix.get().getValor());
                } else {
                    return ResponseEntity.internalServerError().body("createPayments cash payment.");
                }

            } else {
                return ResponseEntity.internalServerError().body("createPayments cash not exist");
            }

        } catch (Exception e) {
            logger.info("createPayments cash: erro", e);
            return ResponseEntity.internalServerError().body("createPayments cash not created");
        }
        logger.info("createPayments cash : end");
        return ResponseEntity.ok().body(ODERCREATE);
    }

}
