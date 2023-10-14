package com.data.percept.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.percept.funtions.createcustumer.CreateContract;

import com.data.percept.funtions.createcustumer.CreateCustumerValidators;


@Entity
public class Custumer {

    public static Logger logger = LoggerFactory.getLogger(Custumer.class);

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_custumer")
    private long idCustumer;

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


    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "numero_contrato")
    private String numberContract;


    @Column(name = "data_recorrencia")
    private String dataRecorrencia;

    @Column(name = "valor")
    private BigDecimal valor;


    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataRecorrencia() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setDataRecorrencia(String dataRecorrencia) {
        this.dataRecorrencia = dataRecorrencia;
    }
    

    public String getNumberContract() {
        return CreateContract.generateContract();
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
        
    } 

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
    public void setCpf(String cpf) throws Exception {

        if (CreateCustumerValidators.isValidCpf(cpf)) {
            logger.info("Custumer: cpf valid.");
            this.cpf = cpf;

        }else{
            throw new Exception("Custumer: cpf invalid.");
        }

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

    @Override
    public String toString() {
        return "Custumer [idCustumer=" + idCustumer + ", account="+ ", name=" + name + ", cpf=" + cpf
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
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    

}
