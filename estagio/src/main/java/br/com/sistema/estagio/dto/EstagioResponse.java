package br.com.sistema.estagio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EstagioResponse(
        Integer idEstagio,
        String objetivo,
        String localEstagio,
        BigDecimal cargaHorariaPrevista,
        LocalDate dataInicio,
        LocalDate dataFim,
        String status,
        Integer periodoId,
        Integer matriculaId,
        Integer instituicaoId,
        Integer docenteId
) { }
