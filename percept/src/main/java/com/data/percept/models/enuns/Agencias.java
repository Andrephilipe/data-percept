package com.data.percept.models.enuns;

public enum Agencias {
    Maranguape(01),
    Outro(02);

    private Integer valor;

    public Integer valor(){
        return valor;
    }

    Agencias(Integer valor){
        this.valor = valor;
    }

    public static Integer getAgencia(String localMunicipio) throws Exception {
        if (localMunicipio.equalsIgnoreCase("Maranguape") ) {
            return Maranguape.valor();
        }
        if (localMunicipio.equalsIgnoreCase("Outro")) {
            return Outro.valor();
        }
        throw new Exception("Agencia não existe");
}
}
