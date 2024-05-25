package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_contatos")
public class Contato {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CONTATOS_SEQ"
    )
    @SequenceGenerator(
            name = "CONTATOS_SEQ",
            sequenceName = "CONTATOS_SEQ",
            allocationSize = 1
    )
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(nome, contato.nome) && Objects.equals(email, contato.email) && Objects.equals(telefone, contato.telefone) && Objects.equals(rua, contato.rua) && Objects.equals(cidade, contato.cidade) && Objects.equals(estado, contato.estado) && Objects.equals(cep, contato.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, rua, cidade, estado, cep);
    }
}
