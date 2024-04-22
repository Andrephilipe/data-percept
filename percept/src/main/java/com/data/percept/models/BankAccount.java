package com.data.percept.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class BankAccount{
    public static Logger logger = LoggerFactory.getLogger(BankAccount.class);

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bank_account")
    private long id;

    @Column(name = "value_positive")
    private BigDecimal saldoPositivo;

    @Column(name = "value_negative")
    private BigDecimal saldoNegativo;

    @Column(name = "balance")
    private BigDecimal saldo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getSaldoPositivo() {
        return saldoPositivo;
    }

    public void setSaldoPositivo(BigDecimal saldoPositivo) {
        this.saldoPositivo = saldoPositivo;
    }

    public BigDecimal getSaldoNegativo() {
        return saldoNegativo;
    }

    public void setSaldoNegativo(BigDecimal saldoNegativo) {
        this.saldoNegativo = saldoNegativo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    
}