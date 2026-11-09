package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.Academico;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicoRepository extends JpaRepository<Academico, Integer> {
    Optional<Academico> findByMatricula(String matricula);

    Optional<Academico> findByCpf(String cpf);
}
