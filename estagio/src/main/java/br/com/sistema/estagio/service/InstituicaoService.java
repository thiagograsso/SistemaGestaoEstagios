package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.repository.InstituicaoRepository;
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
    public Optional<Instituicao> findByCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }
}
