package com.data.percept.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.PerceptApplication;
import com.data.percept.funtions.CpfValidator;
import com.data.percept.models.Custumer;
import com.data.percept.repository.CreateCustumerRepository;

@RestController
public class CreateCustumer {
    public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);

    @Autowired
    private CreateCustumerRepository _custumerRepository;


    @PostMapping("/createCustumer")
	public ResponseEntity<String> createCustumer(@RequestBody Custumer custumer)
    {
        logger.info("createCustumer: start");
        if (CpfValidator.isValid(custumer.getCpf())) {
            logger.info("createCustumer: valid CPF");
        } else {
            return ResponseEntity.badRequest().body("CPF invalid");
        }

		try {
			_custumerRepository.save(custumer);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na conexão com o HTTP createCustumer.");
            System.out.println(e.getMessage());
		}
        return null; 
    }
}
