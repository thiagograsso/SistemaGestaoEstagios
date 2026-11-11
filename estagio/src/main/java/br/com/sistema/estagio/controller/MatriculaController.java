import br.com.sistema.estagio.service.MatriculaService;
import br.com.sistema.estagio.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

package br.com.sistema.estagio.controller;



@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public ResponseEntity<List<?>> listarTodas() {
        return ResponseEntity.ok(matriculaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return matriculaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Object matricula) {
        return ResponseEntity.ok(matriculaService.criar(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Object matricula) {
        return ResponseEntity.ok(matriculaService.atualizar(id, matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}