package br.com.fiap.gestao_residuos.advice;


import br.com.fiap.gestao_residuos.exception.ContatoExistenteException;
import br.com.fiap.gestao_residuos.exception.ContatoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {

        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for(FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }

        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ContatoExistenteException.class)
    public Map<String, String> handleContatoExistenteException(ContatoExistenteException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ContatoNaoEncontradoException.class)
    public Map<String, String> handleContatoNaoEncontradoException(ContatoNaoEncontradoException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }
}
