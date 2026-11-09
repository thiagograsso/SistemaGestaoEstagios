package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.RegistroAtividade;
import br.com.sistema.estagio.repository.RegistroAtividadeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository registroAtividadeRepository;

    public RegistroAtividadeService(RegistroAtividadeRepository registroAtividadeRepository) {
        this.registroAtividadeRepository = registroAtividadeRepository;
    }

    @Transactional(readOnly = true)
    public List<RegistroAtividade> findByEstagioId(Integer idEstagio) {
        return registroAtividadeRepository.findByEstagio_IdEstagioOrderByDataAtividadeDesc(idEstagio);
    }

    @Transactional(readOnly = true)
    public List<RegistroAtividade> findByEstagioIdAndStatus(Integer idEstagio, String status) {
        return registroAtividadeRepository.findByEstagio_IdEstagioAndStatusIgnoreCase(
                idEstagio,
                status);
    }
}
