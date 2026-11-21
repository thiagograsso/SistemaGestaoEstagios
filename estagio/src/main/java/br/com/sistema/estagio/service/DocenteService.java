package br.com.sistema.estagio.service;

import java.util.List;
import java.util.NoSuchElementException;
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
    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Docente buscarPorId(Integer id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Docente não encontrado: " + id));
    }

    @Transactional
    public Docente salvar(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Transactional
    public Docente atualizar(Integer id, Docente docente) {
        buscarPorId(id);
        docente.setIdDocente(id);
        return docenteRepository.save(docente);
    }

    @Transactional
    public void deletar(Integer id) {
        docenteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Docente> findByCpf(String cpf) {
        return docenteRepository.findByCpf(cpf);
    }
}

