package br.com.sistema.estagio.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void recursoNaoEncontradoRetornaNotFoundComEnvelopeDeErro() {
        ResponseEntity<ErroResposta> resposta =
                handler.naoEncontrado(new RecursoNaoEncontradoException("Matrícula não encontrada: 404"));

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals(404, resposta.getBody().status());
        assertEquals("Matrícula não encontrada: 404", resposta.getBody().erro());
        assertEquals(0, resposta.getBody().detalhes().size());
    }
}
