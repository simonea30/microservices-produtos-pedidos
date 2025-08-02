# Desafio DIO - Microserviços com Spring Boot

## O que é este projeto?

Este projeto é um sistema de e-commerce básico usando **microserviços** com Spring Boot. Foi desenvolvido para o desafio da DIO.

## Como funciona?

O sistema tem 4 partes principais:

- **Eureka Server** (porta 8761) - Descobre onde estão os outros serviços
- **API Gateway** (porta 8080) - Porta de entrada única para tudo
- **Serviço de Produtos** (porta 8100) - Gerencia o catálogo de produtos
- **Serviço de Pedidos** (porta 8200) - Cria e consulta pedidos

## Tecnologias usadas

- Java 17
- Spring Boot
- Spring Cloud
- H2 Database (banco em memória)
- Maven

## Como rodar o projeto?

### Pré-requisitos
- Java 17 instalado
- Maven instalado

### Passo a passo

**1. Clone o projeto**
```bash
git clone [URL_DO_REPOSITORIO]
cd api-desafio-dio-microservices
```

**2. Execute na ordem (importante!)**

Abra 4 terminais diferentes e execute um comando em cada:

```bash
# Terminal 1 - Eureka Server
cd api-eureka
mvn spring-boot:run
```

```bash
# Terminal 2 - Serviço de Produtos (aguarde o Eureka subir)
cd api-catalogo
mvn spring-boot:run
```

```bash
# Terminal 3 - Serviço de Pedidos
cd api-solicitacao
mvn spring-boot:run
```

```bash
# Terminal 4 - Gateway
cd api-gateway
mvn spring-boot:run
```

**3. Teste se funcionou**

Acesse: http://localhost:8761 - Deve mostrar todos os serviços registrados.

### Exemplos de uso

**Listar produtos:**
```bash
curl -X GET "http://localhost:8080/produtos" -H "Authorization: Bearer test"
```

**Criar produto:**
```bash
curl -X POST "http://localhost:8080/produtos" \
  -H "Authorization: Bearer test" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Notebook", "descricao": "Notebook Dell", "preco": 2500.00}'
```

**Criar pedido:**
```bash
curl -X POST "http://localhost:8080/solicitacao" \
  -H "Authorization: Bearer test" \
  -H "Content-Type: application/json" \
  -d '{"nomeCliente": "João", "endereco": "Rua A, 123", "itens": [{"productId": 1, "quantidade": 1}]}'
```

**Ver pedidos:**
```bash
curl -X GET "http://localhost:8080/solicitacao" -H "Authorization: Bearer test"
```

## URLs úteis

- **Eureka Dashboard**: http://localhost:8761
- **Documentação da API de Produtos**: http://localhost:8100/swagger-ui.html
- **Documentação da API de Pedidos**: http://localhost:8200/swagger-ui.html
- **Banco H2**: http://localhost:8100/h2-console (user: sa, password: password)

## Estrutura do projeto

```
├── api-eureka/          # Service Discovery
├── api-gateway/         # Gateway com autenticação
├── api-catalogo/        # CRUD de produtos
└── api-solicitacao/     # Gestão de pedidos
```

## O que aprendi com este projeto?

- Como criar microserviços com Spring Boot
- Como usar Service Discovery (Eureka)
- Como criar um API Gateway
- Como fazer comunicação entre microserviços
- Como implementar autenticação centralizada
- Como documentar APIs com Swagger

---

**Desenvolvido Simone Almeida Lima** 🚀
