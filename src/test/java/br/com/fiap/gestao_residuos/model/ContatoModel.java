package br.com.fiap.gestao_residuos.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ContatoModel {
    @Expose(serialize = false)
    private Long id;

    @Expose
    private String nome;

    @Expose
    private String email;

    @Expose
    private String telefone;

    @Expose
    private String rua;

    @Expose
    private String cidade;

    @Expose
    private String estado;

    @Expose
    private String cep;
}

