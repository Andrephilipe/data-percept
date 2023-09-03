package com.data.percept.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.funtions.createcustumer.CreateCustumerValidators;
import com.data.percept.models.Custumer;
import com.data.percept.repository.CreateCustumerRepository;

@RestController
public class CreateCustumer {
    public static Logger logger = LoggerFactory.getLogger(CreateCustumer.class);

    @Autowired
    private CreateCustumerRepository custumerRepository;


    @PostMapping("/createCustumer")
	public ResponseEntity<String> createCustumer(@RequestBody Custumer custumer)
    {
        logger.info("createCustumer: start");
        if (CreateCustumerValidators.isValidCpf(custumer.getCpf())) {
            logger.info("createCustumer: cpf valid");
        }
        else{
            logger.info("createCustumer: cpf invalid");
            return ResponseEntity.internalServerError().body("account not created");
        }
        CreateCustumerValidators.validatorCampos(custumer);

		try {
            logger.info("createCustumer: try");
			custumerRepository.save(custumer);
		} catch (Exception e) {
			logger.info("createCustumer: erro");
			return ResponseEntity.internalServerError().body("account not created");
		}
        return ResponseEntity.ok().body("account created"); 
    }
}
