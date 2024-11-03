package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_lixo")
public class Lixo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LIXO_SEQ"
    )
    @SequenceGenerator(
            name = "LIXO_SEQ",
            sequenceName = "LIXO_SEQ",
            allocationSize = 1
    )
    private Long id;
    private Double capacidade;
    private String localizacao;
    private String tipo;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lixo lixo = (Lixo) o;
        return Objects.equals(id, lixo.id) && Objects.equals(capacidade, lixo.capacidade) && Objects.equals(localizacao, lixo.localizacao) && Objects.equals(tipo, lixo.tipo) && Objects.equals(descricao, lixo.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacidade, localizacao, tipo, descricao);
    }
}
