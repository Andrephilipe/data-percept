package com.data.percept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.percept.models.Endereco;
public interface CepRepository extends JpaRepository<Endereco, Long>{
    
}
