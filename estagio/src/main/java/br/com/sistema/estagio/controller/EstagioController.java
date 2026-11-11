import br.com.sistema.estagio.model.Estagio;
import br.com.sistema.estagio.service.EstagioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

package br.com.sistema.estagio.controller;



@RestController
@RequestMapping("/api/estagios")
public class EstagioController {

    @Autowired
    private EstagioService estagioService;

    @GetMapping
    public ResponseEntity<List<Estagio>> listarTodos() {
        List<Estagio> estagios = estagioService.listarTodos();
        return ResponseEntity.ok(estagios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estagio> buscarPorId(@PathVariable Long id) {
        Estagio estagio = estagioService.buscarPorId(id);
        return ResponseEntity.ok(estagio);
    }

    @PostMapping
    public ResponseEntity<Estagio> criar(@RequestBody Estagio estagio) {
        Estagio novoEstagio = estagioService.criar(estagio);
        return ResponseEntity.ok(novoEstagio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estagio> atualizar(@PathVariable Long id, @RequestBody Estagio estagio) {
        Estagio estagioAtualizado = estagioService.atualizar(id, estagio);
        return ResponseEntity.ok(estagioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estagioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}