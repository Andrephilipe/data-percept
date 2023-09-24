package com.data.percept.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.OrderPaymentsCarnet;

@Repository
public interface PaymentsCarnetRepository extends JpaRepository<OrderPaymentsCarnet, Long> {

    List<OrderPaymentsCarnet> findByNumberContract(String numberContract);
    
}
