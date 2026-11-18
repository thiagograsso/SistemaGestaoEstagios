package br.com.sistema.estagio.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.sistema.estagio.model.Docente;
import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.model.PeriodoAcademico;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class EstagioMapperTest {

    @Test
    void mapsRequestAndEntityWithoutExposingJpaRelationships() {
        EstagioRequest request = new EstagioRequest(
                "Desenvolver um sistema",
                "Univille",
                new BigDecimal("120.50"),
                LocalDate.of(2026, 3, 1),
                LocalDate.of(2026, 12, 1),
                "Ativo",
                10,
                20,
                30,
                40
        );

        PeriodoAcademico periodo = new PeriodoAcademico();
        periodo.setIdPeriodo(10);
        Matricula matricula = new Matricula();
        matricula.setIdMatricula(20);
        Instituicao instituicao = new Instituicao();
        instituicao.setIdInstituicao(30);
        Docente docente = new Docente();
        docente.setIdDocente(40);

        Estagio estagio = EstagioMapper.toEntity(request, periodo, matricula, instituicao, docente);
        estagio.setIdEstagio(1);

        assertEquals(request.objetivo(), estagio.getObjetivo());
        assertEquals(request.localEstagio(), estagio.getLocalEstagio());
        assertEquals(request.cargaHorariaPrevista(), estagio.getCargaHorariaPrevista());
        assertEquals(periodo, estagio.getPeriodo());
        assertEquals(matricula, estagio.getMatricula());
        assertEquals(instituicao, estagio.getInstituicao());
        assertEquals(docente, estagio.getDocente());
        assertEquals(
                new EstagioResponse(
                        1,
                        "Desenvolver um sistema",
                        "Univille",
                        new BigDecimal("120.50"),
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 12, 1),
                        "Ativo",
                        10,
                        20,
                        30,
                        40
                ),
                EstagioMapper.toResponse(estagio)
        );
    }
}
