package br.com.fiap.gestao_residuos.steps;

import br.com.fiap.gestao_residuos.dto.ContatoDTO;
import br.com.fiap.gestao_residuos.dto.ContatoExibicaoDTO;
import br.com.fiap.gestao_residuos.exception.ExistenteException;
import br.com.fiap.gestao_residuos.exception.NaoEncontradoException;
import br.com.fiap.gestao_residuos.model.Contato;
import br.com.fiap.gestao_residuos.repository.ContatoRepository;
import br.com.fiap.gestao_residuos.service.ContatoService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Optional;

public class ContatoSteps {

    private ContatoService contatoService;
    private ContatoRepository contatoRepository;
    private ContatoDTO contatoDTO;
    private Contato contato;
    private String lastErrorMessage;
    private String responseMessage; // Variável para armazenar a mensagem de sucesso
    private int lastStatusCode; // Para armazenar o status code da resposta

    @Given("que não existe um contato com o email {string}")
    public void queNaoExisteUmContatoComOEmail(String email) {
        contatoRepository = Mockito.mock(ContatoRepository.class);
        contatoService = new ContatoService(contatoRepository);
        Mockito.when(contatoRepository.findByEmail(email)).thenReturn(Optional.empty());
    }

    @When("eu salvo um contato com o nome {string}, email {string}, telefone {string}")
    public void euSalvoUmContatoComONomeEmailTelefone(String nome, String email, String telefone) {
        contatoDTO = new ContatoDTO(null, nome, email, telefone, null, null, null, null);
        try {
            responseMessage = contatoService.salvarContato(contatoDTO); // Captura a mensagem de sucesso
        } catch (ExistenteException e) {
            lastErrorMessage = e.getMessage();
        }
    }

    @Then("o contato deve ser salvo com sucesso")
    public void oContatoDeveSerSalvoComSucesso() {
        Mockito.verify(contatoRepository).save(Mockito.any(Contato.class));
    }

    @Given("que existe um contato com o email {string}")
    public void queExisteUmContatoComOEmail(String email) {
        contato = new Contato();
        contato.setEmail(email);
        Mockito.when(contatoRepository.findByEmail(email)).thenReturn(Optional.of(contato));
    }

    @Then("deve ocorrer uma exceção {string}")
    public void deveOcorrerUmaExceção(String mensagem) {
        Assertions.assertEquals(mensagem, lastErrorMessage);
    }

    @Given("que existe um contato com ID {int}")
    public void queExisteUmContatoComID(int id) {
        contato = new Contato();
        contato.setId((long) id);
        Mockito.when(contatoRepository.findById((long) id)).thenReturn(Optional.of(contato));
    }

    @When("eu busco o contato com ID {int}")
    public void euBuscoOContatoComID(int id) {
        try {
            contatoService.buscarContatoPorId((long) id);
        } catch (NaoEncontradoException e) {
            lastErrorMessage = e.getMessage();
        }
    }

    @Then("devo receber os detalhes do contato")
    public void devoReceberOsDetalhesDoContato() {
        ContatoExibicaoDTO result = contatoService.buscarContatoPorId(contato.getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(contato.getEmail(), result.email());
    }

    @Given("que não existe um contato com ID {int}")
    public void queNaoExisteUmContatoComID(int id) {
        Mockito.when(contatoRepository.findById((long) id)).thenReturn(Optional.empty());
    }

    @When("eu envio uma requisição para salvar um contato com os seguintes dados:")
    public void euEnvioUmaRequisiçãoParaSalvarUmContatoComOsSeguintesDados(io.cucumber.datatable.DataTable dataTable) {
        // Extrai os dados da tabela
        var dados = dataTable.asMaps(String.class, String.class).get(0);
        contatoDTO = new ContatoDTO(null, dados.get("nome"), dados.get("email"), dados.get("telefone"), null, null, null, null);
        try {
            responseMessage = contatoService.salvarContato(contatoDTO); // Captura a mensagem de sucesso
            lastStatusCode = 201; // Sucesso
        } catch (ExistenteException e) {
            lastStatusCode = 400; // Erro de existente
            lastErrorMessage = e.getMessage();
        }
    }

    @Then("o status code da resposta para contato deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, lastStatusCode);
    }

    @And("o corpo da resposta deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeveConterAMensagem(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, responseMessage); // Verifica a mensagem de sucesso
    }

    @When("eu envio uma requisição para buscar o contato com ID {int}")
    public void euEnvioUmaRequisiçãoParaBuscarOContatoComID(int id) {
        try {
            contatoService.buscarContatoPorId((long) id);
            lastStatusCode = 200; // Sucesso
        } catch (NaoEncontradoException e) {
            lastStatusCode = 404; // Não encontrado
            lastErrorMessage = e.getMessage();
        }
    }

    @And("devo receber os detalhes do contato:")
    public void devoReceberOsDetalhesDoContato(io.cucumber.datatable.DataTable dataTable) {
        var dadosEsperados = dataTable.asMaps(String.class, String.class).get(0);
        Assertions.assertEquals(dadosEsperados.get("nome"), contato.getNome());
        Assertions.assertEquals(dadosEsperados.get("email"), contato.getEmail());
        Assertions.assertEquals(dadosEsperados.get("telefone"), contato.getTelefone());
    }
}
