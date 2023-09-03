package com.data.percept.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Custumer {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_custumer")
    private long idCustumer;

    @Column(name = "account")
    private String account;
    @Column(name = "name_custumer")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "email")
    private String email;
    

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
    

}
