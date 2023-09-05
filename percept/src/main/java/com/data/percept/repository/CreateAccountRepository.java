package com.data.percept.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.Account;

public interface CreateAccountRepository extends JpaRepository<Account, Long>{
    
}
