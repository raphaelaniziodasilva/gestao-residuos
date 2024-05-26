package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.ColetaAgendadaDTO;
import br.com.fiap.gestao_residuos.dto.ColetaAgendadaExibicaoDTO;
import br.com.fiap.gestao_residuos.model.ColetaAgendada;
import br.com.fiap.gestao_residuos.service.ColetaAgendadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ColetaAgendadaController {

    @Autowired
    private ColetaAgendadaService coletaAgendadaService;

    @PostMapping("/coleta-agendada")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaAgendadaExibicaoDTO salvarColetaAgendada(@RequestBody @Valid ColetaAgendadaDTO coletaAgendadaDto){
        return coletaAgendadaService.salvarColetaAgendada(coletaAgendadaDto);
    }

    @GetMapping("/coleta-agendada")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaAgendada> listarColetasAgendadas(){
        return coletaAgendadaService.listarColetasAgendadas();
    }

    @GetMapping("/coleta-agendada/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaAgendadaExibicaoDTO buscarColetaAgendadaPorId(@PathVariable Long id){
        return coletaAgendadaService.buscarColetaAgendadaPorId(id);
    }

    @GetMapping("/coleta-agendada/data/{dataColeta}")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaAgendadaExibicaoDTO> buscarColetasPorData(@PathVariable LocalDate dataColeta){
        return coletaAgendadaService.buscarColetasPorData(dataColeta);
    }

    @GetMapping("/coleta-agendada/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaAgendadaExibicaoDTO> buscarColetasPorStatus(@PathVariable String status){
        return coletaAgendadaService.buscarColetasPorStatus(status);
    }

    @PutMapping("/coleta-agendada/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaAgendada atualizar(@PathVariable Long id, @Valid @RequestBody ColetaAgendadaDTO coletaAgendadaDto){
        return coletaAgendadaService.atualizar(id, coletaAgendadaDto);
    }

    @DeleteMapping("/coleta-agendada/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        coletaAgendadaService.excluir(id);
    }

    @GetMapping("/coleta-agendada/contar/{contatoId}")
    @ResponseStatus(HttpStatus.OK)
    public long contarColetasPorContato(@PathVariable Long contatoId) {
        return coletaAgendadaService.contarColetasPorContato(contatoId);
    }
}
