package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.percept.models.RemessaCarnet;

@Repository
public interface PaymentsCarnetRepository extends JpaRepository<RemessaCarnet, Long> {
    
}
