package br.com.fiap.gestao_residuos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ContatoExistenteException extends RuntimeException {
    public ContatoExistenteException(String mensagem) {
        super(mensagem);
    }
}
