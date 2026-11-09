package br.com.sistema.estagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instituicao")
    private Integer idInstituicao;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(length = 200)
    private String endereco;

    @Column(length = 100)
    private String contato;

    @OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY)
    private List<Unidade> unidades = new ArrayList<>();

    @OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY)
    private List<Estagio> estagios = new ArrayList<>();

    public Integer getIdInstituicao() { return idInstituicao; }
    public void setIdInstituicao(Integer idInstituicao) { this.idInstituicao = idInstituicao; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public List<Unidade> getUnidades() { return unidades; }
    public void setUnidades(List<Unidade> unidades) { this.unidades = unidades; }
    public List<Estagio> getEstagios() { return estagios; }
    public void setEstagios(List<Estagio> estagios) { this.estagios = estagios; }
}
