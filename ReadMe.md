# FIAP - TECH CHALENGE 3
O Sistema de Agendamento de Serviços de Beleza e Bem-Estar é uma aplicação robusta desenvolvida para gerenciar agendamentos, profissionais e estabelecimentos da área de beleza. O sistema foi construído utilizando uma arquitetura monolítica para facilitar o desenvolvimento inicial e a execução de testes, com um foco especial em qualidade de software e boas práticas como Clean Architecture e TDD (Test-Driven Development).  

A aplicação foi projetada para ser escalável, segura e adaptável, atendendo a necessidades diversas por meio de APIs RESTful bem documentadas e testadas.

# Deploy
Para realizar o deploy do projeto, é necessário realizar o processo abaixo para os casos desejados.

## Pré-requisitos
Certifique-se de que o Maven está instalado e configurado em sua máquina.
Acesse o diretório raiz do projeto antes de executar os comandos.

## Local

``` shell
mvn spring-boot:run
```

## Build

``` shell
mvn clean package -B
```

## Docker

### Para Testes

Para rodar docker em ambiente de testes

``` shell
docker compose up
```

# Execução de Testes com Maven
Para garantir a qualidade do sistema, a aplicação possui diferentes categorias de testes, organizadas em profiles Maven. Cada profile é configurado para executar testes específicos: integração, sistema ou performance. Esses testes podem ser executados diretamente via linha de comando.

## Pré-requisitos
Certifique-se de que o Maven está instalado e configurado em sua máquina.
Acesse o diretório raiz do projeto antes de executar os comandos.

## Perfis Disponíveis
1. Testes unitários
   Profile: default
   Descrição: Valida de forma unitária todos os aspectos estruturais do Sistema.
   Execução:
``` shell
   mvn test
```

2. Testes de Integração
   Profile: integration-test

Descrição: Valida a interação entre os diferentes módulos do sistema.
Requisito: É necessário que haja um ambiente dockerizado deployado, para realizar:

``` shell
docker compose up
```

Execução:

``` shell
mvn test -P integration-test
```


3. Testes de Sistema
   Profile: system-test

Descrição: Simula cenários reais de uso do sistema, seguindo a abordagem de Behavior-Driven Development (BDD) com o framework Cucumber.
Requisito: É necessário que haja um ambiente dockerizado deployado, para realizar:

``` shell
docker compose up
```


Funcionamento:
Os testes utilizam arquivos .feature escritos em Gherkin, que descrevem o comportamento esperado em linguagem natural.
Cada cenário mapeado nos arquivos .feature está associado a uma implementação em Java para validação.
Execução:

``` shell
mvn test -P system-test
```


4. Testes de Performance
   Profile: performance-test

Descrição: Mede o desempenho e a estabilidade do sistema sob carga, utilizando o Gatling.
Requisito: É necessário que haja um ambiente dockerizado deployado, para realizar:

``` shell
docker compose up
```

Execução:

``` shell
mvn gatling:test -Pperformance-test
```


