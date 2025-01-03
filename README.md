# Cadastro de Clientes e Pedidos - Relacionamento One-to-Many e Many-to-One

## Introdução

Este projeto demonstra a implementação de um sistema de cadastro de clientes e seus respectivos pedidos utilizando os relacionamentos **One-to-Many** e **Many-to-One** no JPA (Java Persistence API). Esses relacionamentos são amplamente utilizados para modelar estruturas onde uma entidade pode estar relacionada a várias outras, mantendo consistência e integridade nos dados.

## Descrição do Projeto

A aplicação permite cadastrar clientes e seus pedidos, atualizar informações de pedidos, remover pedidos específicos, e consultar clientes com seus pedidos associados.

Cada cliente pode ter vários pedidos, enquanto cada pedido está associado a um único cliente. Este modelo é implementado utilizando o relacionamento **One-to-Many** na entidade `Cliente` e **Many-to-One** na entidade `Pedido`.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **JPA/Hibernate**
- **H2 Database** (banco de dados em memória para testes)
- **Spring Web**
- **Spring Data JPA**

### Dependências do `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Endpoints do `ClienteController`

### 1. **Cadastro de um Cliente com Pedidos**

- **Endpoint:** `POST /clientes`
- **Request JSON:**

```json
{
  "nome": "Carlos Silva",
  "pedidos": [
    { "descricao": "Pedido 1" },
    { "descricao": "Pedido 2" }
  ]
}
```

- **Response JSON:**

```json
{
  "id": 1,
  "nome": "Carlos Silva",
  "pedidos": [
    {
      "id": 1,
      "descricao": "Pedido 1",
      "cliente_id": 1
    },
    {
      "id": 2,
      "descricao": "Pedido 2",
      "cliente_id": 1
    }
  ]
}
```

### 2. **Atualização de Pedidos de um Cliente**
- **Endpoint:** `PUT /clientes/{clienteId}/pedidos`
- **Request JSON:**

```json
{
    "descricao": "Novo Pedido"
}
```

- **Response JSON em Caso de Sucesso:**

```json
{
  "id": 1,
  "nome": "Carlos Silva",
  "pedidos": [
    {
      "id": 1,
      "descricao": "Pedido 1",
      "cliente_id": 1
    },
    {
      "id": 2,
      "descricao": "Pedido 2",
      "cliente_id": 1
    },
    {
      "id": 3,
      "descricao": "Novo Pedido",
      "cliente_id": 1
    }
  ]
}
```
- **Resposta (caso cliente não for encontrado):**
```json
{
  "timestamp": "2025-01-03T22:40:57.416836700Z",
  "status": 404,
  "error": "Cliente não encontrado",
  "path": "/clientes/10/pedidos"
}
```

### 3. **Remoção de um Pedido do cliente**
- **Endpoint:** `DELETE /clientes/{clienteId}/pedidos/{pedidoId}`

- **Resposta de Sucesso:** `204 No Content`
- **Resposta (caso o cliente não for encontrado):**
```json
{
  "timestamp": "2025-01-03T22:45:00.265135800Z",
  "status": 404,
  "error": "Cliente não encontrado",
  "path": "/clientes/10/pedidos/3"
}
```
- **Resposta (no caso do cliente existir e o pedido não for encontrado):**
```json
{
    "timestamp": "2025-01-03T22:30:12.352773800Z",
    "status": 404,
    "error": "Pedido não encontrado ou não pertence ao cliente",
    "path": "/clientes/1/pedidos/3"
}
```
### 4. **Consulta de todos os Clientes com seus respectivos Pedidos**

- **Endpoint:** `GET /clientes`
- **Response JSON:**

```json
[
    {
        "id": 1,
        "nome": "João Silva",
        "pedidos": [
            { "id": 1, "descricao": "Pedido 1" },
            { "id": 2, "descricao": "Pedido 2" }
        ]
    },
    {
        "id": 2,
        "nome": "Maria Souza",
        "pedidos": []
    }
]
```
### 4. **Atualização de um Pedidos em específico**
- **Endpoint:** `PUT /pedidos/{pedidoId}`

- **Request JSON:**

```json
{
  "descricao": "Pedido atualizado para testar o efeito cascata"
}
```
- **Resposta de Sucesso:**
```json
{
  "id": 2,
  "descricao": "Pedido atualizado para testar o efeito cascata",
  "cliente_id": 1
}
``` 
- **Resposta (caso o pedido não for encontrado):**
```json
{
  "timestamp": "2025-01-03T23:04:16.332874700Z",
  "status": 404,
  "error": "Pedido não encontrado",
  "path": "/pedidos/20"
}
```
## Contato

- **Linkedin:** [Gabriel Caio](https://www.linkedin.com/in/gabriel-caio/)

