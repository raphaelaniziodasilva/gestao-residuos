package br.com.fiap.gestao_residuos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistenteException extends RuntimeException {
    public ExistenteException(String mensagem) {
        super(mensagem);
    }
}
