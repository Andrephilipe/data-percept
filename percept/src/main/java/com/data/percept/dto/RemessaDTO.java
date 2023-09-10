package com.data.percept.dto;

import java.math.BigDecimal;

import com.data.percept.models.RemessaPix;

public class RemessaDTO {

    private String nomeTitular;

    private String cpf;

    private String municipio;

    private String dataVencimento;

    private BigDecimal valor;

    private String dataCriacao;

    private String dataValidade;

    private String statusPix;

    private String stringPix;

    private Integer parcelas;


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
        return dataVencimento;
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
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getStatusPix() {
        return statusPix;
    }

    public void setStatusPix(String statusPix) {
        this.statusPix = statusPix;
    }

    public String getStringPix() {
        return stringPix;
    }

    public void setStringPix(String stringPix) {
        this.stringPix = stringPix;
    }

    public static RemessaDTO convert(RemessaPix remessaPix) {

        RemessaDTO remessaPixDTO = new RemessaDTO();
        remessaPixDTO.setNomeTitular(remessaPix.getNomeTitular());
        remessaPixDTO.setCpf(remessaPix.getCpf());
        remessaPixDTO.setDataCriacao(remessaPix.getDataCriacao());
        remessaPixDTO.setStatusPix(remessaPix.getStatusPix());
        remessaPixDTO.setValor(remessaPix.getValor());

        return remessaPixDTO;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }


}
