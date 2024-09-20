package com.data.percept.funtions.createcustumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.data.percept.models.Account;



public class CreateCustumerValidators {
        public static Logger logger = LoggerFactory.getLogger(CreateCustumerValidators.class);


    public static boolean isValidCpf(String cpf) {
        logger.info("CreateCustumerValidators - isValidCpf: start" + cpf);
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

    public static ResponseEntity<String> validatorCampos(String email){
        logger.info("CreateCustumerValidators - validatorCampos: start");
        try {
            String testValidator = "";
            if (email.equals(testValidator) || email == null){
            return ResponseEntity.internalServerError().body("name obrigatorio"); 
        }
        } catch (Exception e) {
            logger.info("validatorCampos: error" + e);
        }
 
        return null;

    }

    public static String getDataCriacao() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String generateAccountNumber() {
         // Gere um número aleatório de 6 dígitos
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000; // Isso gera um número entre 100000 e 999999
        int digito = random.nextInt(90) + 10;
        return String.valueOf(numero) +"-" + String.valueOf(digito);

    }

    public static String saveAccount(Account account) {
        logger.info("teste account" + account);
        return null;
         // Gere um número aleatório de 6 dígitos

    }
}
