# Agrix API - Projeto Fase A

## Confira todas as fases do projeto aqui:

| Fase   | Link                                                            |
| ------ | --------------------------------------------------------------- |
| Fase A | [Fase A](https://github.com/lucas-de-lima/projeto-agrix-fase-a) |
| Fase B | [Fase B](https://github.com/lucas-de-lima/projeto-agrix-fase-b) |
| Fase C | [Fase C](https://github.com/lucas-de-lima/projeto-agrix-fase-c) |

Bem-vindo ao repositório do projeto Agrix API! Este projeto faz parte da [Fase A](https://github.com/lucas-de-lima/projeto-agrix-fase-a) do desenvolvimento de uma aplicação para controle e gerenciamento de fazendas. Aqui, você encontrará todas as instruções necessárias para configurar, desenvolver e testar essa aplicação, utilizando o ecossistema Spring (Spring Boot, Spring Web, Spring Data, etc).

## Informações Gerais

Este projeto se concentra na criação de uma API RESTful para o gerenciamento de fazendas, plantações e fertilizantes. A seguir, detalharei a estrutura do banco de dados, a implementação da API e as rotas disponíveis.

## Descrição do Banco de Dados

A aplicação tem o seguinte modelo de tabelas:

![Modelo de tabelas](images/agrix-tabelas-fase-a.png)

Neste modelo, temos as seguintes tabelas:

- **`farm`**: representa uma fazenda.
- **`crop`**: representa uma plantação e está em um relacionamento `n:1` (muitos para um) com a tabela `farm`.
- **`fertilizer`**: representa um fertilizante e está em um relacionamento `n:n` (muitos para muitos) com a tabela `crop`. Esse relacionamento é realizado através da tabela `crop_fertilizer`, e aparece pela primeira vez na [Fase B](https://github.com/lucas-de-lima/projeto-agrix-fase-b) do projeto.

## Detalhes do Projeto

### Parte 1: Implementação das Rotas de Fazenda

<details>
	<summary>📍🌐 Detalhes da Rota:</summary><br />
	
### 1. Criação da API para controle de fazendas com a rota POST `/farms`

Aqui é criada a base para o gerenciamento de fazendas da API, utilizando Spring, Spring Boot, Spring Web e Spring Data.
A API foi configurada, incluindo as dependências e classes/camadas necessárias.

#### Detalhes da Rota:

- **`/farms` (`POST`)**
  - Deve receber via corpo do POST os dados de uma fazenda.
  - Deve salvar uma nova fazenda a partir dos dados recebidos.
  - Em caso de sucesso, deve:
    - Retornar o status HTTP 201 (CREATED).
    - Retornar os dados da fazenda criada, incluindo o `id` da fazenda.

#### Exemplo de Requisição:

```json
{
  "name": "Fazendinha",
  "size": 5
}
```

#### Exemplo de Resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```

### 2. Rota GET `/farms`

A rota GET `/farms` foi criada para listar todas as fazendas cadastradas no sistema. Esta rota é essencial para obter uma visão geral de todas as fazendas gerenciadas pela API.

#### Detalhes da Rota:

- **`/farms` (`GET`)**
  - Retorna uma lista de todas as fazendas cadastradas. Cada fazenda incluída na resposta contém seu `id`, `name`, e `size`.

#### Exemplo de Resposta:

```json
[
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Fazenda do Júlio",
    "size": 2.5
  }
]
```

Esta rota permite que os usuários recuperem facilmente todas as fazendas, facilitando a visualização e o gerenciamento dos dados.

### 3. Rota GET `/farms/{id}`

Para acessar os detalhes específicos de uma fazenda, implementamos a rota GET `/farms/{id}`. Essa funcionalidade é crucial para visualizar informações detalhadas de uma fazenda específica.

#### Detalhes da Rota:

- **`/farms/{id}` (`GET`)**
  - Recebe um `id` pelo caminho da rota e retorna a fazenda correspondente.
  - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a mensagem `Fazenda não encontrada!`.

#### Exemplo de Resposta:

Para a rota `/farms/3`, supondo que exista uma fazenda com `id = 3`:

```json
{
  "id": 3,
  "name": "My Cabbages!",
  "size": 3.49
}
```

Essa rota garante que cada fazenda possa ser acessada individualmente, fornecendo detalhes precisos e específicos quando necessário.

</details>

### Parte 2: Implementação das Rotas de Plantação

<details>
	<summary>📍🌐 Detalhes da Rota:</summary><br />
Continuando com o desenvolvimento do Agrix API, agora focamos na integração das plantações com as fazendas, adicionando rotas essenciais para o gerenciamento completo das plantações.

### 4. Rota POST `/farms/{farmId}/crops`

Para criar uma nova plantação associada a uma fazenda, implementamos a rota POST `/farms/{farmId}/crops`. Esta rota permite adicionar plantações específicas a uma fazenda, mantendo a relação `n:1` entre plantações e fazendas.

#### Detalhes da Rota:

- **`/farms/{farmId}/crops` (`POST`)**
  - Recebe o `id` da fazenda pelo caminho da rota (representado aqui por `farmId`).
  - Recebe via corpo do POST os dados da plantação.
  - Salva a nova plantação associada à fazenda com o ID recebido.
  - Em caso de sucesso, retorna o status HTTP 201 (CREATED) e os dados da plantação criada, incluindo o `id` da plantação e o `id` da fazenda.
  - Caso não exista uma fazenda com o `id` passado, a rota retorna o status HTTP 404 com a mensagem `Fazenda não encontrada!`.

#### Exemplo de Requisição:

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43
}
```

#### Exemplo de Resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "farmId": 1
}
```

Esta rota é fundamental para garantir que cada fazenda possa gerenciar suas plantações de forma eficiente e organizada.

### 5. Rota GET `/farms/{farmId}/crops`

Para listar todas as plantações associadas a uma fazenda específica, implementamos a rota GET `/farms/{farmId}/crops`. Esta rota permite visualizar todas as plantações de uma determinada fazenda.

#### Detalhes da Rota:

- **`/farms/{farmId}/crops` (`GET`)**
  - Recebe o `id` de uma fazenda pelo caminho.
  - Retorna uma lista com todas as plantações associadas à fazenda.
  - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a mensagem `Fazenda não encontrada!`.

#### Exemplo de Resposta:

Para a rota `/farms/1/crops`, supondo que exista uma fazenda com `id = 1`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  }
]
```

Com esta rota, os usuários podem obter uma visão detalhada de todas as plantações dentro de uma fazenda específica, facilitando o gerenciamento e a análise das plantações.

</details>

### Parte 3: Rotas para Listagem e Detalhamento de Plantações

<details>
	<summary>📍🌐 Detalhes da Rota:</summary><br />

Para complementar as funcionalidades da Agrix API, implementamos rotas adicionais que permitem a listagem e detalhamento de todas as plantações cadastradas, independentemente da fazenda.

### 6. Rota GET `/crops`

A rota GET `/crops` foi criada para listar todas as plantações cadastradas no sistema. Esta funcionalidade é essencial para obter uma visão geral de todas as plantações gerenciadas pela API.

#### Detalhes da Rota:

- **`/crops` (`GET`)**
  - Retorna uma lista de todas as plantações cadastradas.
  - A resposta inclui o `id` de cada plantação e o `id` da fazenda associada, mas não inclui os dados da fazenda.

#### Exemplo de Resposta:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "farmId": 2
  }
]
```

Esta rota permite que os usuários obtenham uma lista completa de todas as plantações registradas, facilitando a análise e o gerenciamento dos dados.

### 7. Rota GET `/crops/{id}`

Para acessar os detalhes específicos de uma plantação, implementamos a rota GET `/crops/{id}`. Esta funcionalidade é crucial para visualizar informações detalhadas de uma plantação específica.

#### Detalhes da Rota:

- **`/crops/{id}` (`GET`)**
  - Recebe o `id` de uma plantação pelo caminho da rota.
  - Caso exista a plantação com o `id` recebido, retorna os dados da plantação.
  - A resposta inclui o `id` da plantação e o `id` da fazenda associada, mas não inclui os dados da fazenda.
  - Caso não exista uma plantação com o `id` passado, a rota retorna o status HTTP 404 com a mensagem `Plantação não encontrada!`.

#### Exemplo de Resposta:

Para a rota `/crops/3`, supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "farmId": 2
}
```

Com esta rota, os usuários podem acessar informações detalhadas sobre uma plantação específica, facilitando a visualização e o gerenciamento dos dados.

</details>

### Parte 4: Configuração do Dockerfile

<details>
	<summary>📍🌐 Detalhes do Dockerfile:</summary><br />
	
Para garantir que nossa aplicação Agrix API seja facilmente contêinerizada e possa ser executada em qualquer ambiente, criamos um Dockerfile multi-estágio. Esta abordagem nos permite construir e empacotar nossa aplicação de maneira eficiente, aproveitando os benefícios de cache do Docker.

### 8. Dockerfile Multi-Estágio

#### Estágio 1: Construção da Imagem

No primeiro estágio, chamado `build-image`, utilizamos uma imagem base com Maven para construir o pacote JAR da aplicação.

- **Imagem Base:** `maven:3-openjdk-17`
- **Diretório de Trabalho:** `/to-build-app`
- **Passos:**
  1.  Copiar os arquivos necessários.
  2.  Instalar as dependências utilizando Maven.
  3.  Construir o pacote JAR da aplicação.

```dockerfile
# Estágio 1: Build da aplicação
FROM maven:3-openjdk-17 AS build-image
WORKDIR /to-build-app
COPY . .
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests
```

#### Estágio 2: Construção da Imagem Final

No segundo estágio, utilizamos uma imagem de tamanho reduzido para criar a imagem final da aplicação, pronta para execução.

- **Imagem Base:** `eclipse-temurin:17-jre-alpine`
- **Diretório de Trabalho:** `/app`
- **Passos:**
  1.  Copiar o pacote JAR construído no primeiro estágio.
  2.  Expor a porta `8080`.
  3.  Definir o ponto de entrada para executar a aplicação.

```dockerfile
# Estágio 2: Imagem final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /to-build-app/target/agrix-api.jar /app/agrix-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/agrix-api.jar"]
```

#### Testando a Imagem Docker

Para garantir que a imagem Docker funciona corretamente, siga os passos abaixo:

1.  **Construir a Imagem:**

    ```bash
    docker build -t agrix-api .
    ```

2.  **Executar a Imagem:**

    ```bash
    docker run -p 8080:8080 agrix-api
    ```

Com esses comandos, a aplicação será executada no Docker, e você poderá acessá-la em `http://localhost:8080`.

</details>

### Conclusão

Com o Dockerfile multi-estágio, garantimos que a Agrix API possa ser construída, empacotada e executada de maneira eficiente e consistente em qualquer ambiente. Esta abordagem facilita a distribuição e implantação da aplicação, proporcionando um processo de desenvolvimento mais ágil e robusto.
Nesta fase projeto eu apliquei o conhecimento que adquiri, e consegui ser capaz de:
- Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
- Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
- Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco de dados.
- Implementar gerenciamento de erros no Spring Web.
- Criar o Dockerfile para configurar a aplicação para execução no Docker.