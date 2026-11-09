package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.RegistroAtividade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, Integer> {
    List<RegistroAtividade> findByEstagio_IdEstagioOrderByDataAtividadeDesc(Integer idEstagio);

    List<RegistroAtividade> findByEstagio_IdEstagioAndStatusIgnoreCase(
            Integer idEstagio,
            String status);
}
