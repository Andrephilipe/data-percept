package com.data.percept.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.Account;

public interface CreateAccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByCpf(String cpf);

}
