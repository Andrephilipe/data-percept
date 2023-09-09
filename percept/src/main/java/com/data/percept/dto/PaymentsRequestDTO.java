package com.data.percept.dto;

import java.math.BigDecimal;

public class PaymentsRequestDTO {
    
    private BigDecimal valor;

    private Long id;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
