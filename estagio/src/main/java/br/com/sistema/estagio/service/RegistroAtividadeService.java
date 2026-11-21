package br.com.sistema.estagio.service;

import br.com.sistema.estagio.model.RegistroAtividade;
import br.com.sistema.estagio.repository.RegistroAtividadeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository registroAtividadeRepository;

    public RegistroAtividadeService(RegistroAtividadeRepository registroAtividadeRepository) {
        this.registroAtividadeRepository = registroAtividadeRepository;
    }

    @Transactional(readOnly = true)
    public List<RegistroAtividade> listarTodos() {
        return registroAtividadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RegistroAtividade buscarPorId(Integer id) {
        return registroAtividadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Registro de atividade não encontrado: " + id));
    }

    @Transactional
    public RegistroAtividade criar(RegistroAtividade registroAtividade) {
        return registroAtividadeRepository.save(registroAtividade);
    }

    @Transactional
    public RegistroAtividade atualizar(Integer id, RegistroAtividade registroAtividade) {
        buscarPorId(id);
        registroAtividade.setIdRegistro(id);
        return registroAtividadeRepository.save(registroAtividade);
    }

    @Transactional
    public void deletar(Integer id) {
        registroAtividadeRepository.deleteById(id);
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
