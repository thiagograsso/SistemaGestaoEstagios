package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_atividade")
public class RegistroAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @Column(name = "data_atividade", nullable = false)
    private LocalDate dataAtividade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "quantidade_horas", precision = 5, scale = 2, nullable = false)
    private BigDecimal quantidadeHoras;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_submissao")
    private LocalDateTime dataSubmissao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estagio", nullable = false)
    private Estagio estagio;

    public Integer getIdRegistro() { return idRegistro; }
    public void setIdRegistro(Integer idRegistro) { this.idRegistro = idRegistro; }
    public LocalDate getDataAtividade() { return dataAtividade; }
    public void setDataAtividade(LocalDate dataAtividade) { this.dataAtividade = dataAtividade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getQuantidadeHoras() { return quantidadeHoras; }
    public void setQuantidadeHoras(BigDecimal quantidadeHoras) { this.quantidadeHoras = quantidadeHoras; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDataSubmissao() { return dataSubmissao; }
    public void setDataSubmissao(LocalDateTime dataSubmissao) { this.dataSubmissao = dataSubmissao; }
    public Estagio getEstagio() { return estagio; }
    public void setEstagio(Estagio estagio) { this.estagio = estagio; }
}
