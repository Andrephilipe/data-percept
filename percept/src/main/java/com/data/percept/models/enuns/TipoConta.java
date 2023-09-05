package com.data.percept.models.enuns;

public enum TipoConta {
    CORRENTE(1),
    POUPANSA(2);

    private Integer valor;

    public Integer valor(){
        return valor;
    }

    TipoConta(Integer valor){
        this.valor = valor;
    }

    public static String getValor(Integer tipoconta) throws Exception {
            if (tipoconta == 1) {
                return "corrente";
            }
            if (tipoconta == 2) {
                return "poupanca";
            }
            throw new Exception("My Message");
    }
}

