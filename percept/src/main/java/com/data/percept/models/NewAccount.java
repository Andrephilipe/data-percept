package com.data.percept.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewAccount {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_ACCOUNT")
    @Column(name = "id_account")
    private Long id;

    @Column(name = "name_titutular")
    private String nameTitutular;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "tipo_da_conta")
    private String tipoDaConta;

    @Column(name = "agencia")
    private Integer agencia;

	private String dataCriacao;

    @Column
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTitutular() {
        return nameTitutular;
    }

    public void setNameTitutular(String nameTitutular) {
        this.nameTitutular = nameTitutular;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTipoDaConta() {
        return tipoDaConta;
    }

    public void setTipoDaConta(String tipoDaConta) {
        this.tipoDaConta = tipoDaConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
