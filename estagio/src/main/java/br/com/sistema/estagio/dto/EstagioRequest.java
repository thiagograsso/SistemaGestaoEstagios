package br.com.sistema.estagio.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EstagioRequest(
        String objetivo,

        @Size(max = 150, message = "Local do estágio deve ter no máximo 150 caracteres")
        String localEstagio,

        @Digits(integer = 4, fraction = 2, message = "Carga horária prevista deve ter no máximo 4 dígitos inteiros e 2 decimais")
        BigDecimal cargaHorariaPrevista,

        LocalDate dataInicio,
        LocalDate dataFim,

        @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
        String status,

        @NotNull(message = "Período acadêmico é obrigatório")
        Integer periodoId,

        @NotNull(message = "Matrícula é obrigatória")
        Integer matriculaId,

        @NotNull(message = "Instituição é obrigatória")
        Integer instituicaoId,

        @NotNull(message = "Docente é obrigatório")
        Integer docenteId
) { }
