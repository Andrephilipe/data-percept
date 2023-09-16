package com.data.percept.funtions.geraboleto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculateBoletoInstallments {

    public static Logger logger = LoggerFactory.getLogger(CalculateBoletoInstallments.class);

    private CalculateBoletoInstallments() {
        logger.info("CalculateBoletoInstallments start");
        throw new IllegalStateException("CalculateBoletoInstallments");
    }

    public static BigDecimal CalculateBoletoInstallments(BigDecimal valor, Integer parcelas) {
        logger.info("CalculateBoletoInstallments :  start");
        try {
            Boolean reults = verificaValor(valor);

            if (Boolean.TRUE.equals(reults)) {
                logger.info("CalculateBoletoInstallments : criando parcelas.");
                return calculaValor(valor, parcelas);
            }
        } catch (Exception e) {
            logger.error("CalculateBoletoInstallments : erro.", e);
        }

        return valor;

    }

    public static Boolean verificaValor(BigDecimal valor) {
        BigDecimal valorMinimo = new BigDecimal("100.00");
        int comparacao = valor.compareTo(valorMinimo);
        if (comparacao > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static BigDecimal calculaValor(BigDecimal valorTotal, Integer parcelas) {

        BigDecimal taxaDeJuros = new BigDecimal("0.03");

        BigDecimal quantidadeParcelasBigdecimal = new BigDecimal(parcelas);

        BigDecimal primeiroValor = valorTotal.divide(quantidadeParcelasBigdecimal,2, RoundingMode.HALF_UP);
        BigDecimal segundoValor = valorTotal.multiply(taxaDeJuros);

        return primeiroValor.add(segundoValor);

    }
}
