package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "periodo_academico")
public class PeriodoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Integer idPeriodo;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "carga_horaria_minima", precision = 6, scale = 2, nullable = false)
    private BigDecimal cargaHorariaMinima;

    @Column(nullable = false, length = 20)
    private String status;

    @OneToMany(mappedBy = "periodo", fetch = FetchType.LAZY)
    private List<Estagio> estagios = new ArrayList<>();

    public Integer getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Integer idPeriodo) { this.idPeriodo = idPeriodo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public BigDecimal getCargaHorariaMinima() { return cargaHorariaMinima; }
    public void setCargaHorariaMinima(BigDecimal cargaHorariaMinima) { this.cargaHorariaMinima = cargaHorariaMinima; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Estagio> getEstagios() { return estagios; }
    public void setEstagios(List<Estagio> estagios) { this.estagios = estagios; }
}
