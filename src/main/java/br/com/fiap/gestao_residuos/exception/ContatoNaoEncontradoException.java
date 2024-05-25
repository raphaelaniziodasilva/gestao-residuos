package br.com.fiap.gestao_residuos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContatoNaoEncontradoException extends RuntimeException {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
