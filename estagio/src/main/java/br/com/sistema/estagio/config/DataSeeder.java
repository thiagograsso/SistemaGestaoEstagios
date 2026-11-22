package br.com.sistema.estagio.config;

import br.com.sistema.estagio.model.PeriodoAcademico;
import br.com.sistema.estagio.model.Usuario;
import br.com.sistema.estagio.repository.PeriodoAcademicoRepository;
import br.com.sistema.estagio.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner carregarDadosIniciais(UsuarioRepository usuarios,
                                           PeriodoAcademicoRepository periodos) {
        return args -> {
            if (usuarios.count() == 0) {
                usuarios.save(usuario("ana.luiza@univille.br", "estudante123", "ESTUDANTE"));
                usuarios.save(usuario("marcelo.momm@univille.br", "supervisor123", "DOCENTE"));
                usuarios.save(usuario("carlos.lanzer@univille.br", "coordenador123", "COORDENADOR"));
            }

            if (periodos.count() == 0) {
                PeriodoAcademico semestre1 = new PeriodoAcademico();
                semestre1.setNome("2026/1");
                semestre1.setDataInicio(LocalDate.of(2026, 2, 1));
                semestre1.setDataFim(LocalDate.of(2026, 7, 15));
                semestre1.setCargaHorariaMinima(new BigDecimal("120.00"));
                semestre1.setStatus("Encerrado");
                periodos.save(semestre1);

                PeriodoAcademico semestre2 = new PeriodoAcademico();
                semestre2.setNome("2026/2");
                semestre2.setDataInicio(LocalDate.of(2026, 8, 1));
                semestre2.setDataFim(LocalDate.of(2026, 12, 15));
                semestre2.setCargaHorariaMinima(new BigDecimal("120.00"));
                semestre2.setStatus("Ativo");
                periodos.save(semestre2);
            }
        };
    }

    private Usuario usuario(String email, String senha, String perfil) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenhaHash(senha);
        usuario.setPerfil(perfil);
        usuario.setAtivo(true);
        return usuario;
    }
}
