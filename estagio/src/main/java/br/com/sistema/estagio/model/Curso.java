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
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(name = "quantidade_periodos", nullable = false)
    private Short quantidadePeriodos;

    @Column(nullable = false, length = 20)
    private String turno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade unidade;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas = new ArrayList<>();

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Matricula> matriculas = new ArrayList<>();

    public Integer getIdCurso() { return idCurso; }
    public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Short getQuantidadePeriodos() { return quantidadePeriodos; }
    public void setQuantidadePeriodos(Short quantidadePeriodos) { this.quantidadePeriodos = quantidadePeriodos; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public Unidade getUnidade() { return unidade; }
    public void setUnidade(Unidade unidade) { this.unidade = unidade; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Disciplina> disciplinas) { this.disciplinas = disciplinas; }
    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
}
