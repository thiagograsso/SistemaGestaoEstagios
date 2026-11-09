package br.com.sistema.estagio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.estagio.model.Docente;
import br.com.sistema.estagio.repository.DocenteRepository;

@Service
public class DocenteService {
    
    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Docente> findByCpf(String cpf) {
        return docenteRepository.findByCpf(cpf);
    }
}

