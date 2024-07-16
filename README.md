
![forum_hub_logo](https://github.com/user-attachments/assets/33187b57-3667-49e7-8102-a36f90f81243)

# ForumHub
![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=Java&logoColor=white) ![MySQL Badge](https://img.shields.io/badge/MySQL-232F3E?style=for-the-badge&logo=MySQL&logoColor=white) ![Maven Badge](https://img.shields.io/badge/Maven-f24d38?style=for-the-badge&logo=Maven&logoColor=white) ![Spring Badge](https://img.shields.io/badge/Spring_Boot_3-59d141?style=for-the-badge&logo=Spring&logoColor=black) ![JPA Badge](https://img.shields.io/badge/JPA-9cd1d6?style=for-the-badge&logo=JPA&logoColor=black)

O ForumHub é um projeto que implementa uma API REST criada para o challenge Forum HUB do Programa ONE - Oracle Next Education. Ele oferece operações para criar, listar, atualizar e excluir cursos, tópicos, respostas e usuários, além de funcionalidades de autenticação e autorização com tokens JWT.

## Índice 

* [Título e Descrição](#pesquisa-fipe)
* [Índice](#índice)
* [Funcionalidades do Projeto](#-funcionalidades-do-projeto)
* [Tecnologias Utilizadas](#%EF%B8%8F-técnicas-e-tecnologias-utilizadas)
* [Acesso ao Projeto](#-acesso-ao-projeto)
* [Abrir e Rodar o Projeto](#%EF%B8%8F-abrir-e-rodar-o-projeto)
* [Detalhamento do Código Java](#-detalhamento-do-código-java)

## 🔨 Funcionalidades do projeto

O API do ForumHub oferece as seguintes funcionalidades:

- Criação, e login de Usuários, que podem ser user comum ou admins.
- Criação, listagem, detalhamento, atualização e exclusão de Cursos (somente para admins).
- Criação, listagem, detalhamento, atualização e exclusão de Tópicos.
- Criação, listagem, atualização e exclusão de Respostas.
- Utilização do Swagger para a documentação dos endpoints da API.

## ✔️ Técnicas e tecnologias utilizadas

- `Java`: Linguagem principal utilizada no desenvolvimento do projeto.
- `Maven`: Ferramenta de gerenciamento de dependências e build.
- `Spring Boot 3 Framework`: Utilizado para facilitar a criação da aplicação.
- `JPA & MySQL`: Dependências utilizadas para conexão com o BD, e facilitar o acesso e manipulação de dados com o BD usando Hibernate, além de fazer gerenciamento das tabelas e dos dados.
- `Spring Boot`: Framework para criação de aplicações Java.
- `Spring Security & Validation`: Fornecimento de autenticação e autorização; e Suporte para validação de dados.
- `Flyway`: Controle de versão para banco de dados.
- `Lombok`: Biblioteca para reduzir o código boilerplate.
- `Springdoc OpenAPI`: Geração de documentação interativa da API com o Swagger.
- `Java JWT (Auth0)`: Biblioteca para manipulação de tokens JWT.

## 📁 Acesso ao projeto

Você pode acessar o código fonte do projeto [aqui](https://github.com/heitor34studio/Forum_Hub/tree/main/src/main/java/br/com/alura/ForumHub).

## 🛠️ Abrir e rodar o projeto

Para abrir e rodar o projeto, siga os seguintes passos:

1. Clone o repositório para o seu ambiente local:
    ```sh
    git clone https://github.com/heitor34studio/ForumHub.git
    ```

2. Navegue até o diretório do projeto:
    ```sh
    cd Forum_Hub; cd src; cd main; cd java; cd br; cd com; cd alura; cd ForumHub; 
    ```

3. Compile o projeto (ForumHubApplication.java) usando Maven:
    ```sh
    mvn clean install 
    ```

4. Execute o projeto:
    ```sh
    mvn spring-boot:run
    ```

### Detalhamento do código Java:

O código Java fornecido implementa uma API RESTful para gerenciamento de um fórum de perguntas e respostas.

#### controller/*.java
Arquivos classe Java com anotações que os definem como Controllers para o Spring Boot 3, onde gerenciam as operações CRUD para suas respectivas entidades, usando seus próprios endpoints.
- `AutenticacaoController: Endpoint -> "/login", CRUD (somente Read) -> efetuarLogin. `
- `CursoController: Endpoint -> "/cursos", CRUD -> cadastrar, listar, detalhar, atualizar, excluir. `
- `RespostaController: Endpoint -> "/respostas", CRUD -> cadastrar, listar, atualizar, excluir. `
- `TopicoController: Endpoint -> "/topicos", CRUD -> cadastrar, listar, detalhar, atualizar, excluir. `
- `UsuarioController: Endpoint -> "/usuario", CRUD (somente Create) -> createUsuario. `

#### infra/
Package que contem todos os arquivos relacionados à infraestrutura do projeto:
  ###### /dto/
  `Data To Object`, classes `record` que servem para validar os dados passado pelo `JSON` do Front-End, e para modelar o `JSON` que vai ser retornado ao Front-end
  ###### /exception/TratamentoDeErros.java
  Classe que serve para o tratamento de erros que podem ocorrer conforme o uso da API, e retornar as respostas de forma clara para quem for receber o erro.
  ###### /model/
  Classes que modelam as entidades do sistema, como os Cursos, Respostas, Topicos e Usuarios. Também contém Enumerados para Categorias dos Cursos ou para Status de um Tópico.
  ###### /security/
  Arquivos que usando anotações específicas, indicam para o Spring as configurações da segurança da API. Definem quais rotas são públicas e quais requisitam um `Token` e filtram as requisições `HTTP` para confirmar o `Bearer Token`.
  ###### /security/SpringDocConfigurations.java
  Esta classe configura o `SpringDoc` para incluir um esquema de segurança do tipo `Bearer JWT` na documentação da API.

#### repository/
Interfaces que extendem o Repositório `JPA`, e gera as funções com `Querys SQL` para consulta, utilizando `Derived Queries` do próprio JPA, para todas entidades do sistema: Curso, Resposta, Toico, Usuario, e o Role do usuário.

#### service/*.java
Classes de serviço que definem as funções do projeto, tendo um para cada Entidade do sistema: Autenticacao, Curso, Resposta, Topicos, Usuario. Fazem consultas no BD, e fazendo sempre verificações para garantir a integridade dos dados.

#### service/validacoes/
Validadores que garantem que não seja liberado a inserção de um tópico duplicado ou resposta duplicada no BD. O mesmo é aplicado para operações de  update dos mesmos.

### Exemplo de Uso
Ao executar o programa, o usuário pode fazer requisições HTTP para o endereço: http://localhost:8080/{endpoint_escolhido}, e manipular os dados do BD.

###### HTTP Requests para UsuarioController e AutenticacaoController:
https://github.com/user-attachments/assets/49e27fe2-43c0-47d9-b52b-407938860efc


###### HTTP Requests para CursoController:
https://github.com/user-attachments/assets/83cacc3f-fbb1-42af-932b-df9963164001


###### HTTP Requests para TopicoController:
https://github.com/user-attachments/assets/21f94b0d-4d82-4070-9d85-4b560ee84909


###### HTTP Requests para RespostaController:
https://github.com/user-attachments/assets/24a6996e-b0ce-449e-8f10-664822a5c3da


---

Este é o README atualizado para o projeto ForumHub do Programa ONE - Oracle Next Education. Ele fornece uma visão geral do projeto, suas funcionalidades, tecnologias utilizadas e como acessar e rodar o projeto. Para mais detalhes, você pode explorar os arquivos do código fonte mencionados.
