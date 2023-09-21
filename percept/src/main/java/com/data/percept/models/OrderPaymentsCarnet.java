package com.data.percept.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderPaymentsCarnet {

    private static final String FORMATCREATE = "dd-MM-yyyy";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_CARNÊ")
    @Column(name = "id_remessa")
    private Long id;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "cpf_titular")
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

    @Column(name = "status_parcela")
    private String statusCarnet;

    @Column(name = "numero_parcela")
    private Integer parcelas;

    @Column(name = "valor_devido")
    private BigDecimal valorDevedor;

    @Column(name = "saldo_devedor")
    private BigDecimal saldoDevedor;

    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "numero_contrato")
    private String numberContract;
    

    public String getNumberContract() {
        return generateContract();
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
        
    }

    public static String generateContract() {
         // Gere um número aleatório de 6 dígitos
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000;
        //int digito = random.nextInt(90) + 10;
        return "CONTRACT"+"-"+String.valueOf(numero);

    }

    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(BigDecimal saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    @Column(name = "parcelas_restantes")
    private Integer parcelasRestantes;
    
    public BigDecimal getValorDevedor() {
        return valorDevedor;
    }

    public void setValorDevedor(BigDecimal valorDevedor) {
        this.valorDevedor = valorDevedor;
    }

    public Integer getParcelasRestantes() {
        return parcelasRestantes;
    }

    public void setParcelasRestantes(Integer parcelasRestantes) {
        this.parcelasRestantes = parcelasRestantes;
    }

    @Column(name = "valor_parcelas")
    private BigDecimal valorParcelas;
    
    public BigDecimal getValorParcelas() {

        return valorParcelas;
    }

    public void setValorParcelas(BigDecimal valorParcelas) {

        this.valorParcelas = valorParcelas;
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

    public String getStatusCarnet() {
        return statusCarnet;
    }

    public void setStatusCarnet(String statusCarnet) {
        this.statusCarnet = statusCarnet;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    
}
