# ⛽ FuelFlow API

## 📑 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Decisões Técnicas](#-arquitetura-e-decisões-técnicas)
- [Como Executar](#-como-executar)

## 💻 Sobre o Projeto

O FuelFlow API é uma aplicação simples de gerenciamento de abastecimentos de postos de gasolina. O Projeto permite cadastro de tipos de combustíveis e suas respectivas bombas, além do registro detalhado de cada abastecimento realizado. 

## ✨ Funcionalidades

- [x] Gestão de Combustíveis: CRUD completo (Criar, Listar, Alterar, Deletar) de tipos de combustível com preço por litro.
- [x] Controle de Bombas: Gerenciamento de bombas de combustível vinculadas a tipos específicos de produtos.
- [x] Registro de Abastecimentos: Cadastro de operações de abastecimento com captura automática de data e cálculo de valor total.
- [x] Histórico Imutável: Persistência do valor total e preço unitário no momento do abastecimento, garantindo a integridade dos dados mesmo após reajustes de preços.
- [x] Consulta Geral: Endpoints para listagem e consulta de todos os dados cadastrados no sistema.

## 🚀 Tecnologias Utilizadas

- Linguagem: Java 25
- Framework Principal: Spring Boot 4.0.3
- Banco de Dados: PostgreSQL 18.1
- Migrações: Flyway (Versionamento de schema)
- Gerenciador de Dependências: Maven

## 🏗️ Arquitetura e Decisões Técnicas

- Padrão MVC + DTOs: Separação clara entre a lógica de apresentação (Controllers), regras de negócio (Services) e persistência (Repositories), utilizando DTOs para evitar a exposição direta das Entidades.
- UUID: Utilizado como chave primária (id) em todas as tabelas para garantir identificadores únicos universais e aumentar a segurança da API.
- Precisão Financeira (BigDecimal): Uso obrigatório de BigDecimal para valores monetários e litragem, evitando erros de arredondamento comuns em tipos de ponto flutuante.
- Estratégia de Snapshot: O valor total do abastecimento é calculado no servidor e salvo no banco de dados, tornando o registro imutável perante alterações futuras no preço do combustível.
- Global Exception Handler: Tratamento centralizado de erros com respostas JSON padronizadas e códigos HTTP semânticos.

## 🏃 Como Executar

Passo a passo para colocar o projeto de pé:

1. Clone o repositório do projeto

```
git clone https://github.com/jean-batista/fuel-flow.git
```

3. Acesse a pasta do projeto
   
```
cd fuelflow-api
```

4. Compile e instale as dependências

```
mvn clean install
```

6. Execute a aplicação

```
mvn spring-boot:run
```
