package com.data.percept.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.data.percept.controller.CreateCustumerController;
import com.data.percept.models.Account;
import com.data.percept.models.Custumer;
import com.data.percept.repository.CreateAccountRepository;
import com.data.percept.repository.CreateCustumerRepository;
import com.data.percept.repository.NewAccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class CreateCustumerTest {

    @InjectMocks
    private CreateCustumerController createCustumer;

    @Mock
    private CreateCustumerRepository custumerRepository;

    @Mock
    private CreateAccountRepository accountRepository;

    @Mock
    private NewAccountRepository newaccountRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustumer() {
        // Implemente seus testes para o método createCustumer aqui
        // Use Mockito para simular o comportamento do repositório e verifique os resultados esperados
        // Exemplo:
        when(custumerRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        Custumer custumer = new Custumer(); // Crie um objeto Custumer válido para o teste
        ResponseEntity<String> response = createCustumer.createCustumer(custumer);

        assertEquals("account not created", response.getBody());
        // Verifique outras asserções conforme necessário
    }

    @Test
    void testCreateAccount() throws Exception {
        // Implemente seus testes para o método createAccount aqui
        // Use Mockito para simular o comportamento do repositório e verifique os resultados esperados
        // Exemplo:
        when(accountRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(custumerRepository.findByCpf(anyString())).thenReturn(Optional.of(new Custumer()));

        Account newAccount = new Account(); // Crie um objeto Account válido para o teste
        ResponseEntity<String> response = createCustumer.createAccount(newAccount);

        assertEquals("account not created, customer not exist", response.getBody());
        // Verifique outras asserções conforme necessário
    }
}

