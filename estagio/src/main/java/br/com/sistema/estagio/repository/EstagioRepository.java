package br.com.sistema.estagio.repository;

import br.com.sistema.estagio.model.Estagio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, Integer> {
    List<Estagio> findByMatricula_Academico_IdAcademico(Integer idAcademico);

    List<Estagio> findByDocente_IdDocente(Integer idDocente);

    List<Estagio> findByStatusIgnoreCase(String status);
}