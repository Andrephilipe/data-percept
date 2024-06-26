package com.data.percept.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderPaymentsDebito {

    private static final String FORMATCREATE = "dd-MM-yyyy";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_DEBITO")
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
    private BigDecimal valor;

    @Column(name = "data_criacao")
    private String dataCriacao;

    @Column(name = "data_validade")
    private String dataValidade;

    @Column(name = "status_debito")
    private String statusDebito;
    
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
        
        DateFormat dateFormat = new SimpleDateFormat(FORMATCREATE);
        return dateFormat.format(newDate );
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
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

    public String getDataValidade() {
        Date currentDate = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int daysToAdd = 20;
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        Date newDate = calendar.getTime();
        
        DateFormat dateFormat = new SimpleDateFormat(FORMATCREATE);
        return dateFormat.format(newDate );
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getStatusDebito() {
        return statusDebito;
    }

    public void setStatusDebito(String statusDebito) {
        this.statusDebito = statusDebito;
    }
    
}
