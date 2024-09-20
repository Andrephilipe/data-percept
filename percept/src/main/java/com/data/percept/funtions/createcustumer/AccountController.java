package com.data.percept.funtions.createcustumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.data.percept.repository.CreateCustumerRepository;

public class AccountController {

public static Logger logger = LoggerFactory.getLogger(AccountController.class);

 
    
    @Autowired
    public CreateCustumerRepository repository;

    public ResponseEntity<String> createAccount() {
        try {
            logger.info("createCustumer: custumerRepository.findAll()" + repository.findByCpf("49442003402"));

        } catch (Exception e) {
            logger.info("buscarPorEmail: erro r " + e);
        }
        return null;


    }
}