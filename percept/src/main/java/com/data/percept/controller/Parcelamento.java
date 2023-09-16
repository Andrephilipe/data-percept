package com.data.percept.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Parcelamento {

    public static List<Date> gerarParcelas(Date dataInicial, Integer numeroParcelas) {
        List<Date> parcelas = new ArrayList<>();

        // Usamos um objeto Calendar para calcular as datas das parcelas
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicial);
        calendar.add(Calendar.DAY_OF_MONTH, 5);

        // Adicionamos a primeira parcela (data inicial)
        parcelas.add(calendar.getTime());

        // Calculamos o intervalo entre as parcelas (1 mês, por exemplo)
        int intervalo = Calendar.MONTH;

        for (int i = 1; i < numeroParcelas; i++) {
            // Adicionamos o intervalo à data da última parcela
            calendar.add(intervalo, 1);
            parcelas.add(calendar.getTime());
        }

        return parcelas;
    }
}
