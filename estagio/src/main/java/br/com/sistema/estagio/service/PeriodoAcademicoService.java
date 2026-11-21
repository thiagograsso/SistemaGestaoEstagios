package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.PeriodoAcademico;
import br.com.sistema.estagio.repository.PeriodoAcademicoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    public PeriodoAcademicoService(PeriodoAcademicoRepository periodoAcademicoRepository) {
        this.periodoAcademicoRepository = periodoAcademicoRepository;
    }

    @Transactional(readOnly = true)
    public List<PeriodoAcademico> listarTodos() {
        return periodoAcademicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PeriodoAcademico buscarPorId(Integer id) {
        return periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Período acadêmico não encontrado: " + id));
    }

    @Transactional
    public PeriodoAcademico criar(PeriodoAcademico periodo) {
        return periodoAcademicoRepository.save(periodo);
    }

    @Transactional
    public PeriodoAcademico atualizar(Integer id, PeriodoAcademico periodo) {
        buscarPorId(id);
        periodo.setIdPeriodo(id);
        return periodoAcademicoRepository.save(periodo);
    }

    @Transactional
    public void deletar(Integer id) {
        periodoAcademicoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PeriodoAcademico> findByStatus(String status) {
        return periodoAcademicoRepository.findByStatusIgnoreCase(status);
    }
}
