package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.PeriodoAcademico;
import br.com.sistema.estagio.repository.PeriodoAcademicoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    public PeriodoAcademicoService(PeriodoAcademicoRepository periodoAcademicoRepository) {
        this.periodoAcademicoRepository = periodoAcademicoRepository;
    }

    @Transactional(readOnly = true)
    public List<PeriodoAcademico> findByStatus(String status) {
        return periodoAcademicoRepository.findByStatusIgnoreCase(status);
    }
}
