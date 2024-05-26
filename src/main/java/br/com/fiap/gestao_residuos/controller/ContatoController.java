package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.ContatoDTO;
import br.com.fiap.gestao_residuos.dto.ContatoExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/contato")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDTO salvarContato(@RequestBody @Valid ContatoDTO contatoDto){
        return contatoService.salvarContato(contatoDto);
    }

    @GetMapping("/contato")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> listarContatos(){
        return contatoService.listarContatos();
    }

    @GetMapping("/contato/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDTO buscarContatoPorId(@PathVariable Long id){
        return contatoService.buscarContatoPorId(id);
    }

    @GetMapping("/contato/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDTO buscarContatoPorEmail(@PathVariable String email){
        return contatoService.buscarContatoPorEmail(email);
    }

    @PutMapping("/contato/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato){
        return contatoService.atualizar(id, contato);
    }

    @DeleteMapping("/contato/{contatoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long contatoId){
        contatoService.excluir(contatoId);
    }
}
