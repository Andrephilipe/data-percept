package com.data.percept.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Column(name = "account")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String accountNumber;

    @Column(name = "tipo_da_conta")
    private String tipoDaConta;
    @Column(name = "agencia")
    private Integer agencia;

	@Column
	private String dataCriacao;

    public String getDataCriacao() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setDataCriacao(String string) {
        this.dataCriacao = string;
    }
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

    public String getAccount() {
        return accountNumber;
    }

    public void setAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    
}
