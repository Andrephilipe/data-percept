package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.OrderPaymentsBoleto;

@Repository
public interface PaymentsBoletoRepository extends JpaRepository<OrderPaymentsBoleto, Long>{
    
}
