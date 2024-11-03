package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ContatoDTO;
import br.com.fiap.gestao_residuos.dto.ContatoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.ExistenteException;
import br.com.fiap.gestao_residuos.exception.NaoEncontradoException;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.repository.ContatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoServiceTest {

    @InjectMocks
    private ContatoService contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    private ContatoDTO contatoDTO;
    private Contato contato;

    @Test
    public void deveSalvarContatoComSucesso() {
        contatoDTO = new ContatoDTO(1L, "Akaza", "akaza@example.com", "119434454665", "Liberdade", "São Paulo", "SP", "10132-020");
        when(contatoRepository.findByEmail(contatoDTO.email())).thenReturn(Optional.empty());

        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        String resultado = contatoService.salvarContato(contatoDTO);

        assertNotNull(resultado);
        assertEquals("Contato salvo com sucesso!", resultado);
        verify(contatoRepository).save(any(Contato.class));
    }

    @Test
    public void deveLancarExistenteExceptionAoSalvarContatoExistente() {
        contatoDTO = new ContatoDTO(1L, "Akaza", "akaza@example.com", "119434454665", "Liberdade", "São paulo", "SP", "10132-020");
        when(contatoRepository.findByEmail(contatoDTO.email())).thenReturn(Optional.of(new Contato()));

        ExistenteException exception = assertThrows(ExistenteException.class, () -> {
            contatoService.salvarContato(contatoDTO);
        });
        assertEquals("Contato já existe!", exception.getMessage());
    }

    @Test
    public void deveListarContatosComSucesso() {
        when(contatoRepository.findAll()).thenReturn(List.of(new Contato()));
        List<Contato> resultado = contatoService.listarContatos();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void deveBuscarContatoPorIdComSucesso() {
        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        ContatoExibicaoDTO resultado = contatoService.buscarContatoPorId(1L);
        assertNotNull(resultado);
        assertEquals(contato.getId(), resultado.id());
    }

    @Test
    public void deveLancarNaoEncontradoExceptionAoBuscarContatoPorIdInexistente() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            contatoService.buscarContatoPorId(1L);
        });
        assertEquals("Contato não existe!", exception.getMessage());
    }

    @Test
    public void deveBuscarContatoPorEmailComSucesso() {
        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(contato));

        ContatoExibicaoDTO resultado = contatoService.buscarContatoPorEmail("john.doe@example.com");
        assertNotNull(resultado);
        assertEquals(contato.getId(), resultado.id());
    }

    @Test
    public void deveLancarNaoEncontradoExceptionAoBuscarContatoPorEmailInexistente() {
        when(contatoRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            contatoService.buscarContatoPorEmail("john.doe@example.com");
        });
        assertEquals("Contato não existe!", exception.getMessage());
    }

    @Test
    public void deveAtualizarContatoComSucesso() {
        Contato contatoExistente = new Contato();
        contatoExistente.setId(1L);
        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setNome("John Smith");

        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contatoExistente));
        when(contatoRepository.save(contatoExistente)).thenReturn(contatoExistente);

        Contato resultado = contatoService.atualizar(1L, contatoAtualizado);
        assertNotNull(resultado);
        assertEquals("John Smith", resultado.getNome());
        verify(contatoRepository).save(contatoExistente);
    }

    @Test
    public void deveLancarNaoEncontradoExceptionAoAtualizarContatoInexistente() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            contatoService.atualizar(1L, new Contato());
        });
        assertEquals("Contato não encontrado!", exception.getMessage());
    }

    @Test
    public void deveExcluirContatoComSucesso() {
        Contato contato = new Contato();
        contato.setId(1L);
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        contatoService.excluir(1L);
        verify(contatoRepository).delete(contato);
    }

    @Test
    public void deveLancarNaoEncontradoExceptionAoExcluirContatoInexistente() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            contatoService.excluir(1L);
        });
        assertEquals("Contato não encontrado!", exception.getMessage());
    }

}
