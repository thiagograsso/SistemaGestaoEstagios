package br.com.sistema.estagio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "docente_disciplina")
public class DocenteDisciplina {

    @EmbeddedId
    private DocenteDisciplinaId id = new DocenteDisciplinaId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idDocente")
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idDisciplina")
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;

    public DocenteDisciplinaId getId() { return id; }
    public void setId(DocenteDisciplinaId id) { this.id = id; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
}
