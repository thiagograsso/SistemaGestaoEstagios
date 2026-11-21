package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.model.Academico;
import br.com.sistema.estagio.service.AcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/academico")
public class AcademicoController {

    @Autowired
    private AcademicoService academicoService;

    @GetMapping
    public ResponseEntity<List<Academico>> listarTodos() {
        List<Academico> registros = academicoService.listarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Academico> buscarPorId(@PathVariable Integer id) {
        Academico registro = academicoService.buscarPorId(id);
        return ResponseEntity.ok(registro);
    }

    @PostMapping
    public ResponseEntity<Academico> criar(@RequestBody Academico academico) {
        Academico novoRegistro = academicoService.criar(academico);
        return ResponseEntity.ok(novoRegistro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Academico> atualizar(@PathVariable Integer id, @RequestBody Academico academico) {
        Academico registroAtualizado = academicoService.atualizar(id, academico);
        return ResponseEntity.ok(registroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        academicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}