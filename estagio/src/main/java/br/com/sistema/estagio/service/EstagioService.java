package br.com.sistema.estagio.service;

import br.com.sistema.estagio.dto.EstagioMapper;
import br.com.sistema.estagio.dto.EstagioRequest;
import br.com.sistema.estagio.dto.EstagioResponse;
import br.com.sistema.estagio.exception.RecursoNaoEncontradoException;
import br.com.sistema.estagio.model.Docente;
import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.model.PeriodoAcademico;
import br.com.sistema.estagio.repository.DocenteRepository;
import br.com.sistema.estagio.repository.EstagioRepository;
import br.com.sistema.estagio.repository.InstituicaoRepository;
import br.com.sistema.estagio.repository.MatriculaRepository;
import br.com.sistema.estagio.repository.PeriodoAcademicoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstagioService {

    private final EstagioRepository estagioRepository;
    private final PeriodoAcademicoRepository periodoRepository;
    private final MatriculaRepository matriculaRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final DocenteRepository docenteRepository;

    public EstagioService(
            EstagioRepository estagioRepository,
            PeriodoAcademicoRepository periodoRepository,
            MatriculaRepository matriculaRepository,
            InstituicaoRepository instituicaoRepository,
            DocenteRepository docenteRepository
    ) {
        this.estagioRepository = estagioRepository;
        this.periodoRepository = periodoRepository;
        this.matriculaRepository = matriculaRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.docenteRepository = docenteRepository;
    }

    @Transactional(readOnly = true)
    public List<EstagioResponse> listarTodos() {
        return estagioRepository.findAll().stream()
                .map(EstagioMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EstagioResponse buscarPorId(Integer id) {
        Estagio estagio = estagioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estágio não encontrado: " + id));
        return EstagioMapper.toResponse(estagio);
    }

    @Transactional
    public EstagioResponse criar(EstagioRequest request) {
        Estagio estagio = mapearRequest(request);
        return EstagioMapper.toResponse(estagioRepository.save(estagio));
    }

    @Transactional
    public EstagioResponse atualizar(Integer id, EstagioRequest request) {
        if (!estagioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Estágio não encontrado: " + id);
        }
        Estagio estagio = mapearRequest(request);
        estagio.setIdEstagio(id);
        return EstagioMapper.toResponse(estagioRepository.save(estagio));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarPorId(id);
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

    private Estagio mapearRequest(EstagioRequest request) {
        PeriodoAcademico periodo = periodoRepository.findById(request.periodoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Período acadêmico não encontrado: " + request.periodoId()));
        Matricula matricula = matriculaRepository.findById(request.matriculaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Matrícula não encontrada: " + request.matriculaId()));
        Instituicao instituicao = instituicaoRepository.findById(request.instituicaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Instituição não encontrada: " + request.instituicaoId()));
        Docente docente = docenteRepository.findById(request.docenteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Docente não encontrado: " + request.docenteId()));

        return EstagioMapper.toEntity(request, periodo, matricula, instituicao, docente);
    }
}
