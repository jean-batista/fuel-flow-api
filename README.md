# ⛽ FuelFlow API

## 📑 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Decisões Técnicas](#-arquitetura-e-decisões-técnicas)
- [Testes](#-testes)
- [Documentação](#-documentação)
- [Como Executar](#-como-executar)

## 💻 Sobre o Projeto

O FuelFlow API é uma aplicação robusta de gerenciamento de abastecimentos de postos de gasolina. O projeto permite cadastro de tipos de combustíveis e suas respectivas bombas, além do registro detalhado de cada abastecimento realizado, garantindo a integridade e rastreabilidade das operações.

## ✨ Funcionalidades

- [x] **Gestão de Combustíveis**: CRUD completo (Criar, Listar, Alterar, Deletar) de tipos de combustível com preço por litro.
- [x] **Controle de Bombas**: Gerenciamento de bombas de combustível vinculadas a tipos específicos de produtos, com validação de existência do tipo de combustível.
- [x] **Registro de Abastecimentos**: Cadastro de operações de abastecimento com captura automática de data e cálculo de valor total no servidor.
- [x] **Histórico Imutável**: Persistência do valor total e preço unitário no momento do abastecimento. O registro de abastecimento é imutável (não permite alteração), garantindo a integridade fiscal e contábil.
- [x] **Tratamento de Erros**: Respostas de erro padronizadas e amigáveis (JSON) para cenários como recursos não encontrados ou dados inválidos.

## 🚀 Tecnologias Utilizadas

- **Linguagem**: Java 25
- **Framework Principal**: Spring Boot 4.0.3
- **Banco de Dados**: PostgreSQL 18.1
- **Migrações**: Flyway (Versionamento de schema)
- **Documentação**: SpringDoc OpenAPI 3 (Swagger UI)
- **Testes**: JUnit 5, Mockito, RestAssured, Testcontainers
- **Gerenciador de Dependências**: Maven

## 🏗️ Arquitetura e Decisões Técnicas

- **Padrão MVC + Services + DTOs**: Separação clara de responsabilidades.
    - **Controllers**: Camada de exposição da API REST.
    - **Services**: Regras de negócio, validações e orquestração.
    - **Repositories**: Acesso a dados (Spring Data JPA).
    - **DTOs (Request/Response)**: Objetos específicos para entrada e saída de dados, desacoplando a API do modelo de persistência.
- **UUID**: Utilizado como chave primária em todas as tabelas para identificadores universais.
- **Precisão Financeira (BigDecimal)**: Uso obrigatório para valores monetários e litragem.
- **Imutabilidade de Abastecimentos**: Decisão arquitetural de proibir a atualização de registros de `Supply` via API para garantir consistência histórica.
- **Global Exception Handler**: Interceptação centralizada de exceções (`ResourceNotFoundException`, `RequiredObjectIsNullException`) retornando códigos HTTP adequados (404, 400).

## 🧪 Testes

O projeto conta com uma suíte abrangente de testes automatizados:

- **Testes Unitários**: Focados na camada de Service, utilizando Mockito para isolar dependências e validar regras de negócio.
- **Testes de Integração**:
    - **Repositories**: Testam a persistência real usando `@DataJpaTest`.
    - **Controllers**: Testam os endpoints HTTP de ponta a ponta utilizando **RestAssured** e um banco de dados real provido por **Testcontainers**.
    - **Swagger**: Valida a disponibilidade da documentação da API.

## 📖 Documentação

A API possui documentação interativa gerada automaticamente pelo **SpringDoc OpenAPI**.
Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

## 🏃 Como Executar

Passo a passo para colocar o projeto de pé:

1. Clone o repositório do projeto

```
git clone https://github.com/jean-batista/fuel-flow-api.git
```

2. Acesse a pasta do projeto
   
```
cd fuel-flow-api
```

3. Compile e instale as dependências

```
mvn clean install
```

4. Execute a aplicação

```
mvn spring-boot:run
```

Certifique-se de ter um banco de dados PostgreSQL rodando e as variáveis de ambiente configuradas corretamente (ou use o perfil default se configurado).
