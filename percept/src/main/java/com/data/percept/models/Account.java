package com.data.percept.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Account {
    
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

	@Column
    private String cpf;

    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataCriacao() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setDataCriacao(String string) {
    }
    public String getNameTitutular() {
        return nameTitutular;
    }
    public void setNameTitutular(String nameTitutular) throws Exception {

        if(nameTitutular == null || nameTitutular == ""){
            throw new Exception("O Campo Nome e obrigatorio.");
        }else{
            this.nameTitutular = nameTitutular;
        }

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
