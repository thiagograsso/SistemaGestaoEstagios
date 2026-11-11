import br.com.sistema.estagio.model.RegistroAtividade;
import br.com.sistema.estagio.service.RegistroAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

package br.com.sistema.estagio.controller;



@RestController
@RequestMapping("/registro-atividades")
public class RegistroAtividadeController {

    @Autowired
    private RegistroAtividadeService registroAtividadeService;

    @GetMapping
    public ResponseEntity<List<RegistroAtividade>> listarTodos() {
        List<RegistroAtividade> registros = registroAtividadeService.listarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAtividade> buscarPorId(@PathVariable Long id) {
        RegistroAtividade registro = registroAtividadeService.buscarPorId(id);
        return ResponseEntity.ok(registro);
    }

    @PostMapping
    public ResponseEntity<RegistroAtividade> criar(@RequestBody RegistroAtividade registroAtividade) {
        RegistroAtividade novoRegistro = registroAtividadeService.criar(registroAtividade);
        return ResponseEntity.ok(novoRegistro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroAtividade> atualizar(@PathVariable Long id, @RequestBody RegistroAtividade registroAtividade) {
        RegistroAtividade registroAtualizado = registroAtividadeService.atualizar(id, registroAtividade);
        return ResponseEntity.ok(registroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroAtividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}