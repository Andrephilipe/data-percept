package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
    
}
