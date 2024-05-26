package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ColetaAgendadaDTO;
import br.com.fiap.gestao_residuos.dto.ColetaAgendadaExibicaoDTO;
import br.com.fiap.gestao_residuos.dto.LixoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.NaoEncontradoException;
import br.com.fiap.gestao_residuos.model.ColetaAgendada;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.model.Lixo;
import br.com.fiap.gestao_residuos.repository.ColetaAgendadaRepository;
import br.com.fiap.gestao_residuos.repository.ContatoRepository;
import br.com.fiap.gestao_residuos.repository.LixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColetaAgendadaService {
    @Autowired
    private ColetaAgendadaRepository coletaAgendadaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private LixoRepository lixoRepository;

    public ColetaAgendadaExibicaoDTO salvarColetaAgendada(ColetaAgendadaDTO coletaAgendadaDto) {
        ColetaAgendada novaColetaAgendada = new ColetaAgendada();
        novaColetaAgendada.setDataColeta(coletaAgendadaDto.dataColeta());
        novaColetaAgendada.setStatus(coletaAgendadaDto.status());
        novaColetaAgendada.setObservacoes(coletaAgendadaDto.observacoes());

        Contato contato = contatoRepository.findById(coletaAgendadaDto.contatoId())
                .orElseThrow(() -> new NaoEncontradoException("Contato não encontrado!"));

        novaColetaAgendada.setContato(contato);

        Lixo lixo = lixoRepository.findById(coletaAgendadaDto.lixoId())
                .orElseThrow(() -> new NaoEncontradoException("Lixo não encontrado!"));

        novaColetaAgendada.setLixo(lixo);

        return new ColetaAgendadaExibicaoDTO(coletaAgendadaRepository.save(novaColetaAgendada));
    }


    public List<ColetaAgendada> listarColetasAgendadas() {
        return coletaAgendadaRepository.findAll();
    }

    public ColetaAgendadaExibicaoDTO buscarColetaAgendadaPorId(Long id) {
        Optional<ColetaAgendada> coletaAgendadaOptional = coletaAgendadaRepository.findById(id);

        if (coletaAgendadaOptional.isPresent()) {
            return new ColetaAgendadaExibicaoDTO(coletaAgendadaOptional.get());
        } else {
            throw new NaoEncontradoException("Coleta agendada não encontrada!");
        }
    }

    public List<ColetaAgendadaExibicaoDTO> buscarColetasPorData(LocalDate dataColeta) {
        return coletaAgendadaRepository.findByDataColeta(dataColeta).stream()
                .map(ColetaAgendadaExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ColetaAgendadaExibicaoDTO> buscarColetasPorStatus(String status) {
        return coletaAgendadaRepository.findByStatus(status).stream()
                .map(ColetaAgendadaExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public ColetaAgendada atualizar(Long id, ColetaAgendadaDTO coletaAgendadaDto) {
        Optional<ColetaAgendada> coletaAgendadaOptional = coletaAgendadaRepository.findById(id);

        if (coletaAgendadaOptional.isPresent()) {
            ColetaAgendada coletaAgendadaExistente = coletaAgendadaOptional.get();
            coletaAgendadaExistente.setDataColeta(coletaAgendadaDto.dataColeta());
            coletaAgendadaExistente.setStatus(coletaAgendadaDto.status());
            coletaAgendadaExistente.setObservacoes(coletaAgendadaDto.observacoes());

            Contato contato = contatoRepository.findById(coletaAgendadaDto.contatoId())
                    .orElseThrow(() -> new NaoEncontradoException("Contato não encontrado!"));
            coletaAgendadaExistente.setContato(contato);

            Lixo lixo = lixoRepository.findById(coletaAgendadaDto.lixoId())
                    .orElseThrow(() -> new NaoEncontradoException("Lixo não encontrado!"));
            coletaAgendadaExistente.setLixo(lixo);

            return coletaAgendadaRepository.save(coletaAgendadaExistente);
        } else {
            throw new NaoEncontradoException("Coleta agendada não encontrada!");
        }
    }


    public void excluir(Long id) {
        Optional<ColetaAgendada> coletaAgendadaOptional = coletaAgendadaRepository.findById(id);

        if (coletaAgendadaOptional.isPresent()) {
            coletaAgendadaRepository.delete(coletaAgendadaOptional.get());
        } else {
            throw new NaoEncontradoException("Coleta agendada não encontrada!");
        }
    }

    public long contarColetasPorContato(Long contatoId) {
        return coletaAgendadaRepository.countByContatoId(contatoId);
    }
}
