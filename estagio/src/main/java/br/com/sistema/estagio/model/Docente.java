package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Integer idDocente;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 100)
    private String contato;

    @Column(length = 200)
    private String endereco;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<DocenteDisciplina> disciplinas = new ArrayList<>();

    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<Estagio> estagios = new ArrayList<>();

    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<HistoricoAvaliacao> avaliacoes = new ArrayList<>();

    public Integer getIdDocente() { return idDocente; }
    public void setIdDocente(Integer idDocente) { this.idDocente = idDocente; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public List<DocenteDisciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<DocenteDisciplina> disciplinas) { this.disciplinas = disciplinas; }
    public List<Estagio> getEstagios() { return estagios; }
    public void setEstagios(List<Estagio> estagios) { this.estagios = estagios; }
    public List<HistoricoAvaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(List<HistoricoAvaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }
}
