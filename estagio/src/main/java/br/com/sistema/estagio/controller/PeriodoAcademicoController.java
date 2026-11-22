package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.model.PeriodoAcademico;
import br.com.sistema.estagio.service.PeriodoAcademicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoAcademicoController {

    @Autowired
    private PeriodoAcademicoService periodoAcademicoService;

    @GetMapping
    public ResponseEntity<List<PeriodoAcademico>> listarTodos() {
        return ResponseEntity.ok(periodoAcademicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoAcademico> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(periodoAcademicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PeriodoAcademico> criar(@RequestBody PeriodoAcademico periodo) {
        return ResponseEntity.ok(periodoAcademicoService.criar(periodo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoAcademico> atualizar(
            @PathVariable Integer id,
            @RequestBody PeriodoAcademico periodo) {
        return ResponseEntity.ok(periodoAcademicoService.atualizar(id, periodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        periodoAcademicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

