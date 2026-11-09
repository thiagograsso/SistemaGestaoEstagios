package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Academico;
import br.com.sistema.estagio.repository.AcademicoRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcademicoService {

    private final AcademicoRepository academicoRepository;

    public AcademicoService(AcademicoRepository academicoRepository) {
        this.academicoRepository = academicoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Academico> findByCpf(String cpf) {
        return academicoRepository.findByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public Optional<Academico> findByMatricula(String matricula) {
        return academicoRepository.findByMatricula(matricula);
    }
}
