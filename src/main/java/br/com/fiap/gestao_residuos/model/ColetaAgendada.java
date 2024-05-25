package br.com.fiap.gestao_residuos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_coleta_agendada")
public class ColetaAgendada {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COLETA_SEQ"
    )
    @SequenceGenerator(
            name = "COLETA_SEQ",
            sequenceName = "COLETA_SEQ",
            allocationSize = 1
    )
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataColeta;
    private String status;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "contato_id", nullable = false)
    private Contato contato;

    @ManyToOne
    @JoinColumn(name = "lixo_id", nullable = false)
    private Lixo lixo;

    public Long getId() {
        return id;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Lixo getLixo() {
        return lixo;
    }

    public void setLixo(Lixo lixo) {
        this.lixo = lixo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColetaAgendada that = (ColetaAgendada) o;
        return Objects.equals(id, that.id) && Objects.equals(dataColeta, that.dataColeta) && Objects.equals(status, that.status) && Objects.equals(observacoes, that.observacoes) && Objects.equals(contato, that.contato) && Objects.equals(lixo, that.lixo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataColeta, status, observacoes, contato, lixo);
    }
}
