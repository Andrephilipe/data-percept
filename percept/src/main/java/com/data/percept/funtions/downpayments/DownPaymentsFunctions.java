package com.data.percept.funtions.downpayments;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.models.BankAccount;
import com.data.percept.models.RemessaPix;
import com.data.percept.repository.BankAccountRepository;
import com.data.percept.repository.PaymentsPixRepository;

@RestController
public class DownPaymentsFunctions {
    public static Logger logger = LoggerFactory.getLogger(DownPaymentsFunctions.class);

    @Autowired
    static PaymentsPixRepository paymentsPixRepository;

    @Autowired
    static
    BankAccountRepository bankAccountRepository;

    public static void bankBalance(Long id, BigDecimal valor) {

        try {
            Long newLong = id;
            logger.info("bankBalance pix: start new long" + newLong);
            Optional<RemessaPix> paymentPix = paymentsPixRepository.findById(newLong);
            logger.info("bankBalance pix: start" + paymentPix);
            BankAccount newSaldo = new BankAccount();
            newSaldo.setSaldo(valor);
            bankAccountRepository.save(newSaldo);

            if (paymentPix.isPresent()) {
                logger.info("bankBalance pix: id present");
                RemessaPix verificaStatus = new RemessaPix();
                verificaStatus = paymentPix.get();
                if (verificaStatus.getStatusPix().equalsIgnoreCase("pago")) {
                    logger.info("bankBalance pix: id pago");
 
                }
                logger.info("bankBalance pix: start");
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("bankBalance erro : " + e);
        }
    }

}
