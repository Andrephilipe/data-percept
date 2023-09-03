package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.Custumer;

public interface CreateCustumerRepository extends JpaRepository<Custumer, Long>{
    
}
