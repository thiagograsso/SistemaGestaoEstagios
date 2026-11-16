package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.repository.EstagioRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstagioService {

    private final EstagioRepository estagioRepository;

    public EstagioService(EstagioRepository estagioRepository) {
        this.estagioRepository = estagioRepository;
    }

    @Transactional(readOnly = true)
    public List<Estagio> listarTodos() {
        return estagioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estagio buscarPorId(Integer id) {
        return estagioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estágio não encontrado: " + id));
    }

    @Transactional
    public Estagio criar(Estagio estagio) {
        return estagioRepository.save(estagio);
    }

    @Transactional
    public Estagio atualizar(Integer id, Estagio estagio) {
        buscarPorId(id);
        estagio.setIdEstagio(id);
        return estagioRepository.save(estagio);
    }

    @Transactional
    public void deletar(Integer id) {
        estagioRepository.deleteById(id);
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
