package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.CounterAccount;

@Repository
public interface CounterAccountRepository extends JpaRepository<CounterAccount, Long>{
    
}
