package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.OrderPaymentsDebito;

@Repository
public interface PaymentsDebitoRepository extends JpaRepository<OrderPaymentsDebito, Long> {
    
}
