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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Integer idMatricula;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_academico", nullable = false)
    private Academico academico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "matricula", fetch = FetchType.LAZY)
    private List<Estagio> estagios = new ArrayList<>();

    public Integer getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public Academico getAcademico() { return academico; }
    public void setAcademico(Academico academico) { this.academico = academico; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public List<Estagio> getEstagios() { return estagios; }
    public void setEstagios(List<Estagio> estagios) { this.estagios = estagios; }
}
