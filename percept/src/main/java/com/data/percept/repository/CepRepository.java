package com.data.percept.repository;

import org.springframework.data.repository.CrudRepository;

import com.data.percept.models.Endereco;
public interface CepRepository extends CrudRepository<Endereco, Integer>{
    
}
