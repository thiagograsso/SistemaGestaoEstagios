package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.model.Docente;
import br.com.sistema.estagio.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<Docente>> listarTodos() {
        List<Docente> docentes = docenteService.listarTodos();
        return ResponseEntity.ok(docentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscarPorId(@PathVariable Integer id) {
        Docente docente = docenteService.buscarPorId(id);
        return ResponseEntity.ok(docente);
    }

    @PostMapping
    public ResponseEntity<Docente> criar(@RequestBody Docente docente) {
        Docente novoDocente = docenteService.salvar(docente);
        return ResponseEntity.ok(novoDocente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> atualizar(@PathVariable Integer id, @RequestBody Docente docente) {
        Docente docenteAtualizado = docenteService.atualizar(id, docente);
        return ResponseEntity.ok(docenteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        docenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
