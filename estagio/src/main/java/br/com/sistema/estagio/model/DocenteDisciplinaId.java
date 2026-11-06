package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DocenteDisciplinaId implements Serializable {

    @Column(name = "id_docente")
    private Integer idDocente;

    @Column(name = "id_disciplina")
    private Integer idDisciplina;

    public DocenteDisciplinaId() {
    }

    public DocenteDisciplinaId(Integer idDocente, Integer idDisciplina) {
        this.idDocente = idDocente;
        this.idDisciplina = idDisciplina;
    }

    public Integer getIdDocente() { return idDocente; }
    public void setIdDocente(Integer idDocente) { this.idDocente = idDocente; }
    public Integer getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(Integer idDisciplina) { this.idDisciplina = idDisciplina; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DocenteDisciplinaId that)) return false;
        return Objects.equals(idDocente, that.idDocente)
                && Objects.equals(idDisciplina, that.idDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocente, idDisciplina);
    }
}
