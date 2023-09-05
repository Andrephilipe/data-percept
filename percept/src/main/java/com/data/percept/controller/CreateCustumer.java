package com.data.percept.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.funtions.createcustumer.CreateCustumerValidators;
import com.data.percept.models.Account;
import com.data.percept.models.Custumer;
import com.data.percept.models.enuns.Agencias;
import com.data.percept.models.enuns.TipoConta;
import com.data.percept.repository.CreateAccountRepository;
import com.data.percept.repository.CreateCustumerRepository;

@RestController
@Validated
public class CreateCustumer {
    public static Logger logger = LoggerFactory.getLogger(CreateCustumer.class);

    @Autowired
    private CreateCustumerRepository custumerRepository;

    @Autowired
    private CreateAccountRepository accountRepository;

    @PostMapping("/createCustumer")
	public ResponseEntity<String> createCustumer(@Valid @RequestBody Custumer custumer)
    {
        Account accountCreate = new Account();
        logger.info("createCustumer: start" + custumer);
        if (CreateCustumerValidators.isValidCpf(custumer.getCpf())) {
            logger.info("createCustumer: cpf valid");

        }
        else{
            logger.info("createCustumer: cpf invalid");
            return ResponseEntity.internalServerError().body("account not created");
        }
		try {

            Optional<Custumer> cpf = custumerRepository.findByCpf(custumer.getCpf());

            if (cpf.isPresent()) {
                 logger.info("createCustumer: cpf is presents" + cpf);
                 return ((BodyBuilder) ResponseEntity.noContent()).body("account not created");
            }
            

            logger.info("createCustumer: create customer");
            custumer.setDataCriacao(CreateCustumerValidators.getDataCriacao());
			custumerRepository.save(custumer);
            Optional<Custumer> accountBusca = custumerRepository.findByCpf(custumer.getCpf());
            accountCreate.setNameTitutular(custumer.getName());
            accountCreate.setTipoDaConta(TipoConta.getValor(custumer.getTipoConta()));
            accountCreate.setAgencia(Agencias.getAgencia(custumer.getLocalMunicipio()));
            accountCreate.setDataCriacao(CreateCustumerValidators.getDataCriacao());
            accountCreate.setAccount(CreateCustumerValidators.generateAccountNumber());
            accountRepository.save(accountCreate);

            Custumer pessoa = accountBusca.get();
            pessoa.setAccount("dddf23");
            custumerRepository.save(pessoa);

		} catch (Exception e) {
			logger.info("createCustumer: erro" + e);
			return ResponseEntity.internalServerError().body("account not created");
		}
        return ResponseEntity.ok().body("account created"); 
    }
}
