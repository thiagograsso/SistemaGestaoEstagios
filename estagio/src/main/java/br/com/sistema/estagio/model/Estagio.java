package br.com.sistema.estagio.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estagio")
public class Estagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estagio")
    private Integer idEstagio;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    @Column(name = "local_estagio", length = 150)
    private String localEstagio;

    @Column(name = "carga_horaria_prevista", precision = 6, scale = 2)
    private BigDecimal cargaHorariaPrevista;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_matricula", nullable = false)
    private Matricula matricula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_instituicao", nullable = false)
    private Instituicao instituicao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    @OneToMany(mappedBy = "estagio")
    private List<RegistroAtividade> registros = new ArrayList<>();

    public Integer getIdEstagio() { return idEstagio; }
    public void setIdEstagio(Integer idEstagio) { this.idEstagio = idEstagio; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public String getLocalEstagio() { return localEstagio; }
    public void setLocalEstagio(String localEstagio) { this.localEstagio = localEstagio; }
    public BigDecimal getCargaHorariaPrevista() { return cargaHorariaPrevista; }
    public void setCargaHorariaPrevista(BigDecimal cargaHorariaPrevista) { this.cargaHorariaPrevista = cargaHorariaPrevista; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public PeriodoAcademico getPeriodo() { return periodo; }
    public void setPeriodo(PeriodoAcademico periodo) { this.periodo = periodo; }
    public Matricula getMatricula() { return matricula; }
    public void setMatricula(Matricula matricula) { this.matricula = matricula; }
    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public List<RegistroAtividade> getRegistros() { return registros; }
    public void setRegistros(List<RegistroAtividade> registros) { this.registros = registros; }
}