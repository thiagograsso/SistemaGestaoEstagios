import br.com.sistema.estagio.model.Instituicao;
import br.com.sistema.estagio.service.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

package br.com.sistema.estagio.controller;



@RestController
@RequestMapping("/api/instituicoes")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping
    public ResponseEntity<List<Instituicao>> listarTodas() {
        List<Instituicao> instituicoes = instituicaoService.listarTodas();
        return ResponseEntity.ok(instituicoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> buscarPorId(@PathVariable Long id) {
        Instituicao instituicao = instituicaoService.buscarPorId(id);
        return ResponseEntity.ok(instituicao);
    }

    @PostMapping
    public ResponseEntity<Instituicao> criar(@RequestBody Instituicao instituicao) {
        Instituicao novaInstituicao = instituicaoService.criar(instituicao);
        return ResponseEntity.ok(novaInstituicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> atualizar(@PathVariable Long id, @RequestBody Instituicao instituicao) {
        Instituicao instituicaoAtualizada = instituicaoService.atualizar(id, instituicao);
        return ResponseEntity.ok(instituicaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        instituicaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}