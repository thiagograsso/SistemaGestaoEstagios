package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(nullable = false, length = 20)
    private String perfil; // ESTUDANTE, DOCENTE, COORDENADOR

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Academico academico;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Docente docente;

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Academico getAcademico() { return academico; }
    public void setAcademico(Academico academico) { this.academico = academico; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
}