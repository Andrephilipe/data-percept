package com.data.percept.funtions.createcustumer;

import java.util.Random;

public class CreateContract {
    
        public static String generateContract() {
         // Gere um número aleatório de 6 dígitos
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000;
        //int digito = random.nextInt(90) + 10;
        return "CONTRACT"+"-"+String.valueOf(numero);

    }

}
