package com.data.percept.funtions.createcustumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.percept.models.Custumer;
import com.data.percept.repository.CreateCustumerRepository;

import java.util.Optional;

@Service
public class CreateAccountService {

    private final CreateCustumerRepository usuarioRepository;

    @Autowired
    public CreateAccountService(CreateCustumerRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Custumer> findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}