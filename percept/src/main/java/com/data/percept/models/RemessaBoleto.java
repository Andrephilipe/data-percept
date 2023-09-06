package com.data.percept.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RemessaBoleto {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_BOLETO")
    @Column(name = "id_remessa")
    private Long id;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "cpf_titutar")
    private String cpf;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "data_vencimento")
    private String dataVencimento;

    @Column(name = "valor")
    private String valor;

    @Column(name = "data_criacao")
    private String dataCriacao;

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

    public String getDataVencimento() {
        Date currentDate = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int daysToAdd = 5;
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        Date newDate = calendar.getTime();
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
     

        return dateFormat.format(newDate );
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
