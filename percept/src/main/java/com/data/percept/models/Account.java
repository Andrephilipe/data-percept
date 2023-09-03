package com.data.percept.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_titutular")
    private String nameTitutular;
    
    @Column(name = "number_account")
    private String account;

    @Column(name = "tipo_da_conta")
    private String tipoDaConta;
    @Column(name = "agencia")
    private Integer agencia;
    public String getNameTitutular() {
        return nameTitutular;
    }
    public void setNameTitutular(String nameTitutular) {
        this.nameTitutular = nameTitutular;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumberAccount() {
        return account;
    }
    public void setNumberAccount(String account) {
        this.account = account;
    }

    
}
