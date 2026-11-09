package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.Instituicao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
    Optional<Instituicao> findByCnpj(String cnpj);
}
