package com.data.percept.funtions.createorderpayments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DueDateOrderPayments {

    public static List<Date> gerarParcelas(Date dataInicial, Integer numeroParcelas) {
        List<Date> parcelas = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicial);
        calendar.add(Calendar.DAY_OF_MONTH, 15);

        parcelas.add(calendar.getTime());

        int intervalo = Calendar.MONTH;

        for (int i = 1; i < numeroParcelas; i++) {
            calendar.add(intervalo, 1);
            parcelas.add(calendar.getTime());
        }

        return parcelas;
    }

    public static String formatDate(Date dueDate){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dueDate);

    }
}
