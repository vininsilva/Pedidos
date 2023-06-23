# Controle de Pedidos

Esta é uma aplicação de controle de pedidos, desenvolvida em Java utilizando o framework Spring Boot e banco de dados MySQL. A aplicação permite receber pedidos e consultar pedidos existentes.

## Requisitos
- Java JDK 8 ou superior
- Maven
- MySQL

## Configuração do Banco de Dados

1. Crie um banco de dados MySQL chamado controle_pedidos.
2. Abra o arquivo application.properties localizado na pasta src/main/resources.
3 .Configure as informações de conexão com o banco de dados, como URL, nome de usuário e senha.

## Executando a Aplicação

1. Abra o terminal na pasta raiz do projeto.
2. Execute o comando mvn spring-boot:run para iniciar a aplicação.
3. A aplicação estará disponível em http://localhost:8080.

## Funcionalidades
### 1. Receber Pedidos

- Endpoint: POST /api/pedidos
- Payload: JSON ou XML contendo os detalhes do pedido

 #### Exemplo de Payload JSON: 
 
> {
  "numeroControle": "123456",
  "dataCadastro": "2023-06-22",
  "nome": "Cliente A",
  "valor": 100.00,
  "quantidade": 5,
  "codigoCliente": 1
}

#### Exemplo de Payload XML:

[Uploading XML.xml…]()

### 2. Consultar Pedidos

- Endpoint: GET /api/pedidos

Parâmetros de consulta:

> numeroPedido (opcional): Filtra os pedidos pelo número de controle.

> dataCadastro (opcional): Filtra os pedidos pela data de cadastro.

#### Exemplo de consulta: 
- GET /api/pedidos
- GET /api/pedidos?numeroPedido=123456
- GET /api/pedidos?dataCadastro=2023-06-22
