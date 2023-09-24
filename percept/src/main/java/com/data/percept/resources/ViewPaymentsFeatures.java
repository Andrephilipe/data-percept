package com.data.percept.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import com.data.percept.models.OrderPaymentsCarnet;
import com.data.percept.repository.PaymentsCarnetRepository;

@Validated
@RestController
@RequestMapping("/ViewsPayments")
public class ViewPaymentsFeatures {
    public static final Logger logger = LoggerFactory.getLogger(ViewPaymentsFeatures.class);
    
    @Autowired
    PaymentsCarnetRepository paymentsCarnetRepository;

    @GetMapping("/carnet/{id}")
    public Optional<OrderPaymentsCarnet> getPaymentCarnet(@PathVariable Long id) {

        try {

            Optional<OrderPaymentsCarnet> remessaCarnet = paymentsCarnetRepository.findById(id);
            return remessaCarnet;

        } catch (Exception e) {
            logger.error("getPaymentCarnet : erro ", e);
            return null;
        }

    }

    @GetMapping("/carnets/{numberContract}")
    public List<String> getPaymentCarnets(@PathVariable String numberContract) {
        try {
            List<OrderPaymentsCarnet> carnets = paymentsCarnetRepository.findByNumberContract(numberContract);
            List<String> carnetsAsString = new ArrayList<>();
            for (OrderPaymentsCarnet carnet : carnets) {

                carnetsAsString.add(carnet.toString());
            }
            
            return carnetsAsString;
        } catch (Exception e) {
            logger.error("getPaymentCarnet: erro", e);
             List<String> carnetsAsString = new ArrayList<>();
             
            carnetsAsString.add("{\"error\": \"Ocorreu um erro ao processar a solicitação.\"}");
             
            return carnetsAsString;
        }
    }
}
