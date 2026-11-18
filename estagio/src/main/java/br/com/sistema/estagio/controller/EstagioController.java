package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.dto.EstagioRequest;
import br.com.sistema.estagio.dto.EstagioResponse;
import br.com.sistema.estagio.service.EstagioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/estagios")
public class EstagioController {

    private final EstagioService estagioService;

    public EstagioController(EstagioService estagioService) {
        this.estagioService = estagioService;
    }

    @GetMapping
    public ResponseEntity<List<EstagioResponse>> listarTodos() {
        List<EstagioResponse> estagios = estagioService.listarTodos();
        return ResponseEntity.ok(estagios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstagioResponse> buscarPorId(@PathVariable Integer id) {
        EstagioResponse estagio = estagioService.buscarPorId(id);
        return ResponseEntity.ok(estagio);
    }

    @PostMapping
    public ResponseEntity<EstagioResponse> criar(@Valid @RequestBody EstagioRequest request) {
        EstagioResponse novoEstagio = estagioService.criar(request);
        return ResponseEntity.ok(novoEstagio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstagioResponse> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EstagioRequest request
    ) {
        EstagioResponse estagioAtualizado = estagioService.atualizar(id, request);
        return ResponseEntity.ok(estagioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        estagioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}