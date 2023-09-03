package com.data.percept.funtions.createcustumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.data.percept.models.Custumer;

public class CreateCustumerValidators {
        public static Logger logger = LoggerFactory.getLogger(CreateCustumerValidators.class);

    public static boolean isValidCpf(String cpf) {
        logger.info("CreateCustumerValidators - isValidCpf: start");
            cpf = cpf.replaceAll("[^0-9]", "");
        
            if (cpf.length() != 11) {
                return false;
            }
        
            int[] digits = new int[11];
            for (int i = 0; i < 11; i++) {
                digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }
        
            // Calcula o primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += digits[i] * (10 - i);
            }
            int remainder = 11 - (sum % 11);
            if (remainder == 10 || remainder == 11) {
                remainder = 0;
            }
            if (remainder != digits[9]) {
                return false;
            }
        
            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += digits[i] * (11 - i);
            }
            remainder = 11 - (sum % 11);
            if (remainder == 10 || remainder == 11) {
                remainder = 0;
            }
            if (remainder != digits[10]) {
                return false;
            }
        logger.info("CreateCustumerValidators - isValidCpf: end");
            return true;
        
        
    }

    public static ResponseEntity<String> validatorCampos(@RequestBody Custumer custumer){
        logger.info("CreateCustumerValidators - validatorCampos: start" + custumer);
        try {
            if (custumer.getName() == null){
            return ResponseEntity.internalServerError().body("name obrigatorio"); 
        }
        if (custumer.getCpf().isEmpty()){
            return ResponseEntity.internalServerError().body("cpf obrigatorio"); 
        }
        if (custumer.getEmail().isEmpty()){
            return ResponseEntity.internalServerError().body("email obrigatorio"); 
        }
        if (custumer.getRg().isEmpty()){
            return ResponseEntity.internalServerError().body("rg obrigatorio"); 
        }
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("validatorCampos: error" + e);
        }
 
        return null;

    }
}
