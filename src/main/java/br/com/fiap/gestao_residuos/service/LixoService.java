package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.LixoDTO;
import br.com.fiap.gestao_residuos.dto.LixoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.NaoEncontradoException;
import br.com.fiap.gestao_residuos.model.Lixo;
import br.com.fiap.gestao_residuos.repository.LixoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LixoService {
    @Autowired
    private LixoRepository lixoRepository;

    public LixoExibicaoDTO salvarLixo(LixoDTO lixoDto) {
        Lixo novoLixo = new Lixo();
        BeanUtils.copyProperties(lixoDto, novoLixo);
        return new LixoExibicaoDTO(lixoRepository.save(novoLixo));
    }

    public List<Lixo> listarLixos() {
        return lixoRepository.findAll();
    }

    public LixoExibicaoDTO buscarLixoPorId(Long id) {
        Optional<Lixo> lixoOptional = lixoRepository.findById(id);

        if (lixoOptional.isPresent()) {
            return new LixoExibicaoDTO(lixoOptional.get());
        } else {
            throw new NaoEncontradoException("Lixo não encontrado!");
        }
    }

    public List<LixoExibicaoDTO> buscarLixoPorTipo(String tipo) {
        return lixoRepository.findByTipo(tipo).stream()
                .map(LixoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public List<LixoExibicaoDTO> buscarLixoPorLocalizacao(String localizacao) {
        return lixoRepository.findByLocalizacao(localizacao).stream()
                .map(LixoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public LixoExibicaoDTO atualizar(Long id, LixoDTO lixoDto) {
        Optional<Lixo> lixoOptional = lixoRepository.findById(id);

        if (lixoOptional.isPresent()) {
            Lixo lixoExistente = lixoOptional.get();
            BeanUtils.copyProperties(lixoDto, lixoExistente, "id");
            return new LixoExibicaoDTO(lixoRepository.save(lixoExistente));
        } else {
            throw new NaoEncontradoException("Lixo não encontrado!");
        }
    }

    public void excluir(Long id) {
        Optional<Lixo> lixoOptional = lixoRepository.findById(id);

        if (lixoOptional.isPresent()) {
            lixoRepository.delete(lixoOptional.get());
        } else {
            throw new NaoEncontradoException("Lixo não encontrado!");
        }
    }
}
