package com.data.percept.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderPaymentsCash {

    private static final String FORMATCREATE = "dd-MM-yyyy";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id_remessa")
    private Long id;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "cpf_titutar")
    private String cpf;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_criacao")
    private String dataCriacao;

    @Column(name = "data_pagamento")
    private String dataPagmento;

    @Column(name = "status_pagamento")
    private String statusPagmento;

    public String getStatusPagmento() {
        return statusPagmento;
    }

    public void setStatusPagmento(String statusPagmento) {
        this.statusPagmento = statusPagmento;
    }

    public String getDataPagmento() {
        return dataPagmento;
    }

    public void setDataPagmento(String dataPagmento) {
        this.dataPagmento = dataPagmento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataCriacao() {
        DateFormat dateFormat = new SimpleDateFormat(FORMATCREATE);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
  
}
