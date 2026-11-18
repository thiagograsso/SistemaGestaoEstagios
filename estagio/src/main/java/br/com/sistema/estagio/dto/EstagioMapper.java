package br.com.sistema.estagio.dto;

import br.com.sistema.estagio.model.Docente;
import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.model.PeriodoAcademico;

public final class EstagioMapper {

    private EstagioMapper() { }

    public static Estagio toEntity(
            EstagioRequest request,
            PeriodoAcademico periodo,
            Matricula matricula,
            Instituicao instituicao,
            Docente docente
    ) {
        Estagio estagio = new Estagio();
        estagio.setObjetivo(request.objetivo());
        estagio.setLocalEstagio(request.localEstagio());
        estagio.setCargaHorariaPrevista(request.cargaHorariaPrevista());
        estagio.setDataInicio(request.dataInicio());
        estagio.setDataFim(request.dataFim());
        estagio.setStatus(request.status());
        estagio.setPeriodo(periodo);
        estagio.setMatricula(matricula);
        estagio.setInstituicao(instituicao);
        estagio.setDocente(docente);
        return estagio;
    }

    public static EstagioResponse toResponse(Estagio estagio) {
        return new EstagioResponse(
                estagio.getIdEstagio(),
                estagio.getObjetivo(),
                estagio.getLocalEstagio(),
                estagio.getCargaHorariaPrevista(),
                estagio.getDataInicio(),
                estagio.getDataFim(),
                estagio.getStatus(),
                estagio.getPeriodo().getIdPeriodo(),
                estagio.getMatricula().getIdMatricula(),
                estagio.getInstituicao().getIdInstituicao(),
                estagio.getDocente().getIdDocente()
        );
    }
}
