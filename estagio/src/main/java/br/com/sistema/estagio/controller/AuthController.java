package br.com.sistema.estagio.controller;

import br.com.sistema.estagio.model.Usuario;
import br.com.sistema.estagio.repository.UsuarioRepository;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.getOrDefault("email", "").trim();
        String senha = credenciais.getOrDefault("senha", "");

        return usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(email)
                .filter(usuario -> usuario.getSenhaHash().equals(senha))
                .map(usuario -> ResponseEntity.ok(Map.of(
                        "id", usuario.getIdUsuario(),
                        "email", usuario.getEmail(),
                        "perfil", usuario.getPerfil().equals("DOCENTE") ? "supervisor" : usuario.getPerfil().toLowerCase())))
                .orElse(ResponseEntity.status(401).body(Map.of("erro", "E-mail ou senha inválidos")));
    }
}

