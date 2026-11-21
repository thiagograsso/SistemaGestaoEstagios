package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.repository.MatriculaRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional(readOnly = true)
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Matricula> buscarPorId(Integer id) {
        return matriculaRepository.findById(id);
    }

    @Transactional
    public Matricula criar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula atualizar(Integer id, Matricula matricula) {
        matriculaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Matrícula não encontrada: " + id));
        matricula.setIdMatricula(id);
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public void deletar(Integer id) {
        matriculaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Matricula> findByAcademicoId(Integer idAcademico) {
        return matriculaRepository.findByAcademico_IdAcademico(idAcademico);
    }
}
