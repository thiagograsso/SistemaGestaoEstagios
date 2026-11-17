package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.exception.RecursoNaoEncontradoException;
import br.com.sistema.estagio.repository.InstituicaoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @Transactional(readOnly = true)
    public List<Instituicao> listarTodas() {
        return instituicaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Instituicao buscarPorId(Integer id) {
        return instituicaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instituição não encontrada: " + id));
    }

    @Transactional
    public Instituicao criar(Instituicao instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    @Transactional
    public Instituicao atualizar(Integer id, Instituicao instituicao) {
        buscarPorId(id);
        instituicao.setIdInstituicao(id);
        return instituicaoRepository.save(instituicao);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarPorId(id);
        instituicaoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Instituicao> findByCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }
}
