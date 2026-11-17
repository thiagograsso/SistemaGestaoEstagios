package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Academico;
import br.com.sistema.estagio.repository.AcademicoRepository;
import br.com.sistema.estagio.exception.RecursoNaoEncontradoException;
import java.util.List;
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
    public List<Academico> listarTodos() {
        return academicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Academico buscarPorId(Integer id) {
        return academicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Acadêmico não encontrado: " + id));
    }

    @Transactional
    public Academico criar(Academico academico) {
        return academicoRepository.save(academico);
    }

    @Transactional
    public Academico atualizar(Integer id, Academico academico) {
        buscarPorId(id);
        academico.setIdAcademico(id);
        return academicoRepository.save(academico);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarPorId(id);
        academicoRepository.deleteById(id);
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
