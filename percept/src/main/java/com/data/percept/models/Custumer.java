package com.data.percept.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
public class Custumer {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_custumer")
    private long idCustumer;

    @Column(name = "account")
    private String account;

    @NotBlank(message = "O campo nome não pode estar em branco")
    @Column(name = "name_custumer")
    private String name;
    
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;

    @Column(name = "email")
    @Email(message = "O campo email deve ser um endereço de e-mail válido")
    private String email;

    private Integer tipoConta;

    private String localMunicipio;

	@Column
	private String dataCriacao;
    

    public Integer getTipoConta() {
        return tipoConta;
    }
    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }
    public String getLocalMunicipio() {
        return localMunicipio;
    }
    public void setLocalMunicipio(String localMunicipio) {
        this.localMunicipio = localMunicipio;
    }
    public long getIdCustumer() {
        return idCustumer;
    }
    public void setIdCustumer(long idCustumer) {
        this.idCustumer = idCustumer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    @Override
    public String toString() {
        return "Custumer [idCustumer=" + idCustumer + ", account=" + account + ", name=" + name + ", cpf=" + cpf
                + ", rg=" + rg + ", email=" + email + "]";
    }
 
    public String getDataCriacao() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    

}
