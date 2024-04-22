package com.data.percept.controller;

import java.util.Optional;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.funtions.createcustumer.CreateCustumerValidators;
import com.data.percept.models.Account;
import com.data.percept.models.Custumer;
import com.data.percept.models.enuns.Agencias;
import com.data.percept.models.enuns.TipoConta;
import com.data.percept.repository.CreateAccountRepository;
import com.data.percept.repository.CreateCustumerRepository;
import com.data.percept.repository.NewAccountRepository;

import io.swagger.annotations.ApiOperation;

@Validated
@Controller
@RestController
@RequestMapping("/")
public class CreateCustumerController {
    public static Logger logger = LoggerFactory.getLogger(CreateCustumerController.class);

    private static final String ACCOUNT_CREATE = "account created";
    @Autowired
    private CreateCustumerRepository custumerRepository;

    @Autowired
    private CreateAccountRepository accountRepository;

    @Autowired
    private NewAccountRepository newaccountRepository;

    @ApiOperation(value = "Endpoint Salvar Pessoas")
    @PostMapping("/createCustumer")
    public ResponseEntity<String> createCustumer(@Valid @RequestBody Custumer custumer) {

        logger.info("createCustumer: start");
        Account accountCreate = new Account();
        try {

            String numberAccount = CreateCustumerValidators.generateAccountNumber();
            Optional<Custumer> cpf = custumerRepository.findByCpf(custumer.getCpf());
            if (cpf.isPresent()) {
                logger.info("createCustumer: cpf is presents");
                return ((BodyBuilder) ResponseEntity.noContent()).body(ACCOUNT_CREATE);
            }

            logger.info("createCustumer: create customer");
            custumer.setDataCriacao(CreateCustumerValidators.getDataCriacao());
            custumer.setAccount(numberAccount);
            custumer.setDataRecorrencia(custumer.getDataRecorrencia());
            custumer.setNumberContract(custumer.getNumberContract());
            custumer.setValor(custumer.getValor());
            custumerRepository.save(custumer);

            accountCreate.setNameTitutular(custumer.getName());
            accountCreate.setTipoDaConta(TipoConta.getValor(custumer.getTipoConta()));
            accountCreate.setAgencia(Agencias.getAgencia(custumer.getLocalMunicipio()));
            accountCreate.setDataCriacao(custumer.getDataCriacao());
            accountCreate.setAccount(numberAccount);
            accountCreate.setCpf(custumer.getCpf());
            accountRepository.save(accountCreate);

        } catch (Exception e) {

            logger.info("createCustumer: erro", e);
            return ResponseEntity.internalServerError().body("account not created");
        }
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account newAccount) throws Exception {
        Account accountCreate = new Account();
        try {
            logger.info("createAccount: create account");
            logger.info("createAccount: start");

            Optional<Account> cpf = accountRepository.findByCpf(newAccount.getCpf());
            Optional<Custumer> customerExist = custumerRepository.findByCpf(newAccount.getCpf());

            if (customerExist.isPresent()) {
                logger.info("createAccount: customer.isPresent");
            } else {
                logger.info("createAccount: customer not exist");
                return ResponseEntity.internalServerError()
                        .body("account not created, customer not exist");
            }

            int numeroEmInteiro = Integer.parseInt(newAccount.getTipoDaConta());
            logger.info("createAccount: get cpf");
            if (cpf.get() != null) {
                Account cpfAccount = cpf.get();

                String test = TipoConta.getValor(numeroEmInteiro);
                if (cpf.isPresent()) {
                    logger.info("createAccount: cpf.isPresent");

                    if (test.equals(cpfAccount.getTipoDaConta())) {
                        logger.info("createAccount: account exist");
                        return ResponseEntity.internalServerError()
                                .body("account not created, account exist" + cpfAccount.getAccount());
                    } else {
                        logger.info("createAccount: cpf nao existe.");
                    }

                } else {
                    logger.info("createAccount: cpf nao existe.");
                }

                int agenciaNew = cpfAccount.getAgencia();
                String newCpf = cpfAccount.getCpf();
                String number = CreateCustumerValidators.generateAccountNumber();
                accountCreate.setAccount(number);
                accountCreate.setCpf(newCpf);
                accountCreate.setNameTitutular(newAccount.getNameTitutular());
                accountCreate.setTipoDaConta(TipoConta.getValor(numeroEmInteiro));
                accountCreate.setAgencia(agenciaNew);
                accountCreate.setDataCriacao(newAccount.getDataCriacao());
                newaccountRepository.save(accountCreate);
            }

        } catch (Exception e) {
            logger.info("createCustumer: erro", e);
            return ResponseEntity.internalServerError().body("account not created");
        }
        return ResponseEntity.ok().body(ACCOUNT_CREATE);
    }

}
