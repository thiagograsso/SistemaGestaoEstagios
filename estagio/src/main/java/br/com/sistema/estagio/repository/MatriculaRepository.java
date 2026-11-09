package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.Matricula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByAcademico_IdAcademico(Integer idAcademico);
}
