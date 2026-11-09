package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.repository.EstagioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstagioService {

    private final EstagioRepository estagioRepository;

    public EstagioService(EstagioRepository estagioRepository) {
        this.estagioRepository = estagioRepository;
    }

    @Transactional(readOnly = true)
    public List<Estagio> findByAcademicoId(Integer idAcademico) {
        return estagioRepository.findByMatricula_Academico_IdAcademico(idAcademico);
    }

    @Transactional(readOnly = true)
    public List<Estagio> findByDocenteId(Integer idDocente) {
        return estagioRepository.findByDocente_IdDocente(idDocente);
    }

    @Transactional(readOnly = true)
    public List<Estagio> findByStatus(String status) {
        return estagioRepository.findByStatusIgnoreCase(status);
    }
}
