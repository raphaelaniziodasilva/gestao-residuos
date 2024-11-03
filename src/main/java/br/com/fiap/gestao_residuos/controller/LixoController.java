package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.LixoDTO;
import br.com.fiap.gestao_residuos.dto.LixoExibicaoDTO;
import br.com.fiap.gestao_residuos.service.LixoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LixoController {

    @Autowired
    private LixoService lixoService;

    @PostMapping("/lixo")
    @ResponseStatus(HttpStatus.CREATED)
    public String salvarLixo(@RequestBody @Valid LixoDTO lixoDTO) {
        return lixoService.salvarLixo(lixoDTO);
    }

    @GetMapping("/lixo")
    @ResponseStatus(HttpStatus.OK)
    public List<LixoExibicaoDTO> listarLixos() {
        return lixoService.listarLixos().stream()
                .map(LixoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/lixo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LixoExibicaoDTO buscarLixoPorId(@PathVariable Long id) {
        return lixoService.buscarLixoPorId(id);
    }

    @GetMapping("/lixo/tipo/{tipo}")
    @ResponseStatus(HttpStatus.OK)
    public List<LixoExibicaoDTO> buscarLixoPorTipo(@PathVariable String tipo) {
        return lixoService.buscarLixoPorTipo(tipo);
    }

    @GetMapping("/lixo/localizacao/{localizacao}")
    @ResponseStatus(HttpStatus.OK)
    public List<LixoExibicaoDTO> buscarLixoPorLocalizacao(@PathVariable String localizacao) {
        return lixoService.buscarLixoPorLocalizacao(localizacao);
    }

    @PutMapping("/lixo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LixoExibicaoDTO atualizar(@PathVariable Long id, @RequestBody @Valid LixoDTO lixoDto) {
        return lixoService.atualizar(id, lixoDto);
    }

    @DeleteMapping("/lixo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        lixoService.excluir(id);
    }
}
