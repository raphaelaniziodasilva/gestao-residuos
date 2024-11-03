package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ContatoDTO;
import br.com.fiap.gestao_residuos.dto.ContatoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.ExistenteException;
import br.com.fiap.gestao_residuos.exception.NaoEncontradoException;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    private final ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public String salvarContato(ContatoDTO contatoDTO) throws ExistenteException {
        if (contatoRepository.findByEmail(contatoDTO.email()).isPresent()) {
            throw new ExistenteException("Contato já existe.");
        }

        Contato contato = new Contato();
        contato.setNome(contatoDTO.nome());
        contato.setEmail(contatoDTO.email());
        contato.setTelefone(contatoDTO.telefone());
        // Defina outros atributos se necessário

        contatoRepository.save(contato);
        return "Contato salvo com sucesso!";
    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public ContatoExibicaoDTO buscarContatoPorId(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDTO(contatoOptional.get());
        } else {
            throw new NaoEncontradoException("Contato não existe!");
        }
    }

    public ContatoExibicaoDTO buscarContatoPorEmail(String email) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(email);

        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDTO(contatoOptional.get());
        } else {
            throw new NaoEncontradoException("Contato não existe!");
        }
    }

    public Contato atualizar(Long id, Contato contato) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            Contato contatoExistente = contatoOptional.get();
            BeanUtils.copyProperties(contato, contatoExistente, "id");
            return contatoRepository.save(contatoExistente);
        } else {
            throw new NaoEncontradoException("Contato não encontrado!");
        }
    }

    public void excluir(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new NaoEncontradoException("Contato não encontrado!");
        }
    }
}
