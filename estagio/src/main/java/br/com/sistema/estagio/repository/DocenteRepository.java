package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.Docente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByCpf(String cpf);
}
