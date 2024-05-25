package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ContatoDTO;
import br.com.fiap.gestao_residuos.dto.ContatoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.ContatoExistenteException;
import br.com.fiap.gestao_residuos.exception.ContatoNaoEncontradoException;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoExibicaoDTO salvarContato(ContatoDTO contatoDto){
        Optional<Contato> contatoExistente = contatoRepository.findByEmail(contatoDto.email());

        if (contatoExistente.isPresent()) {
            throw new ContatoExistenteException("Contato já existe!");
        }

        Contato novoContato = new Contato();
        BeanUtils.copyProperties(contatoDto, novoContato);
        return new ContatoExibicaoDTO(contatoRepository.save(novoContato));
    }

    public List<Contato> listarContatos(){
        return contatoRepository.findAll();
    }

    public ContatoExibicaoDTO buscarContatoPorId(Long id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()){
            return new ContatoExibicaoDTO(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Contato não existe!");
        }
    }

    public ContatoExibicaoDTO buscarContatoPorEmail(String email) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(email);

        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDTO(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Contato não existe!");
        }
    }

    public Contato atualizar(Long id, Contato contato){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()){
            Contato contatoExistente = contatoOptional.get();
            BeanUtils.copyProperties(contato, contatoExistente, "id");
            return contatoRepository.save(contatoExistente);
        } else {
            throw new ContatoNaoEncontradoException("Contato não encontrado!");
        }
    }


    public void excluir(Long id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()){
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Contato não encontrado!");
        }
    }
}
