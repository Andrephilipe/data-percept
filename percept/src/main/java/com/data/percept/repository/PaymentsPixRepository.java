package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.OrderPaymentsPix;

public interface PaymentsPixRepository extends JpaRepository<OrderPaymentsPix, Long> {
    
}
