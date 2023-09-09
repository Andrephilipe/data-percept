package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.RemessaPix;

@Repository
public interface PaymentsPixRepository extends JpaRepository<RemessaPix, Long> {
    
}
