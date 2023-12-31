package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.OrderPaymentsCash;

@Repository
public interface PaymentsCashRepository extends JpaRepository<OrderPaymentsCash, Long>{
    
}
