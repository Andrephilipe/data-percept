package com.data.percept.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.Custumer;

public interface CreateCustumerRepository extends JpaRepository<Custumer, Long>{

    Optional<Custumer> findByCpf(String cpf);
    
}
