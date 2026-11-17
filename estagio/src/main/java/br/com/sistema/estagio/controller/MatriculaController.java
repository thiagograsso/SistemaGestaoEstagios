package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.model.Matricula;
import br.com.sistema.estagio.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodas() {
        return ResponseEntity.ok(matriculaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(matriculaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.criar(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable Integer id, @RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.atualizar(id, matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}