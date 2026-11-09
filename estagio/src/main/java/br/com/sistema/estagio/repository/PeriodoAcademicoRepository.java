package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.PeriodoAcademico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Integer> {
    List<PeriodoAcademico> findByStatusIgnoreCase(String status);
}
