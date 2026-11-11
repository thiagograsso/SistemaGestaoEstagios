import br.com.sistema.estagio.model.RegistroAcademico;
import br.com.sistema.estagio.service.RegistroAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

package br.com.sistema.estagio.controller;



@RestController
@RequestMapping("/api/registro-academico")
public class RegistroAcademicoController {

    @Autowired
    private RegistroAcademicoService registroAcademicoService;

    @GetMapping
    public ResponseEntity<List<RegistroAcademico>> listarTodos() {
        List<RegistroAcademico> registros = registroAcademicoService.listarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAcademico> buscarPorId(@PathVariable Long id) {
        RegistroAcademico registro = registroAcademicoService.buscarPorId(id);
        return ResponseEntity.ok(registro);
    }

    @PostMapping
    public ResponseEntity<RegistroAcademico> criar(@RequestBody RegistroAcademico registroAcademico) {
        RegistroAcademico novoRegistro = registroAcademicoService.criar(registroAcademico);
        return ResponseEntity.ok(novoRegistro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroAcademico> atualizar(@PathVariable Long id, @RequestBody RegistroAcademico registroAcademico) {
        RegistroAcademico registroAtualizado = registroAcademicoService.atualizar(id, registroAcademico);
        return ResponseEntity.ok(registroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroAcademicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}