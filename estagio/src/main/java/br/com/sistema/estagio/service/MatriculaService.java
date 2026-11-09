package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.repository.MatriculaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional(readOnly = true)
    public List<Matricula> findByAcademicoId(Integer idAcademico) {
        return matriculaRepository.findByAcademico_IdAcademico(idAcademico);
    }
}
