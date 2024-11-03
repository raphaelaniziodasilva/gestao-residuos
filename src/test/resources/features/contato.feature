# language: en
@regressivo
Feature: Gerenciamento de Contatos
  Como usuário da API
  Quero cadastrar um novo contato
  Para que o registro seja salvo corretamente no sistema

  Scenario: Salvar um novo contato
    Given que não existe um contato com o email "mel@example.com"
    When eu envio uma requisição para salvar um contato com os seguintes dados:
      | campo   | valor            |
      | nome    | mel              |
      | email   | mel@example.com  |
      | telefone| 1193456789       |
    Then o status code da resposta para contato deve ser 201
    And o corpo da resposta deve conter a mensagem "Contato salvo com sucesso!"

  Scenario: Tentar salvar um contato que já existe
    Given que existe um contato com o email "mel@example.com"
    When eu envio uma requisição para salvar um contato com os seguintes dados:
      | campo   | valor            |
      | nome    | mel              |
      | email   | mel@example.com  |
      | telefone| 1193456789       |
    Then o status code da resposta para contato deve ser 400
    And o corpo da resposta deve conter a mensagem "Contato já existe!"

  Scenario: Buscar um contato por ID
    Given que existe um contato com ID 1
    When eu envio uma requisição para buscar o contato com ID 1
    Then o status code da resposta para contato deve ser 200
    And devo receber os detalhes do contato:
      | campo   | valor             |
      | nome    | akaza             |
      | email   | akaza@example.com |
      | telefone| 1234232327        |

  Scenario: Buscar um contato por ID que não existe
    Given que não existe um contato com ID 1
    When eu envio uma requisição para buscar o contato com ID 1
    Then o status code da resposta para contato deve ser 404
    And o corpo da resposta deve conter a mensagem "Contato não existe!"
