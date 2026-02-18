# Coupon Outforce

---

## Objetivo

Este projeto é um desafio técnico desenvolvido para a Outforce. O objetivo é fornecer uma API REST para o gerenciamento de cupons de desconto de um e-commerce. A aplicação permite criar, consultar e desativar (soft delete) cupons, garantindo validações de regras de negócio como unicidade de código, datas de validade e valores mínimos de desconto.

## Tecnologias

*   Java 17
*   Spring Boot 3
*   H2 Database (Testes/Dev)
*   Flyway (Migração de banco de dados)
*   Docker & Docker Compose com PostgreSQL

## Como Executar

### Pré-requisitos
*   Java 17+
*   Docker e Docker Compose

### Passos

1.  Suba o banco de dados PostgreSQL via Docker:
    ```bash
    docker-compose up -d
    ```

A aplicação estará disponível em `http://localhost:8080/ecommerce/api`.

---

## Documentação da API

Abaixo estão os detalhes de como interagir com os endpoints disponíveis.

### 1. Criar Cupom

Cria um novo cupom de desconto.

*   **URL:** `/ecommerce/api/coupon`
*   **Método:** `POST`
*   **Regras:**
    *   `code`: Deve conter exatamente 6 caracteres alfanuméricos.
    *   `discountValue`: Deve ser maior que 0.5.
    *   `expirationDate`: Deve ser uma data futura.

**Exemplo de Requisição (Body):**

```json
{
  "code": "PROMO1",
  "description": "Cupom de desconto de verão",
  "discountValue": 15.00,
  "expirationDate": "2025-12-31T23:59:59",
  "published": true
}
```

**Retorno Esperado (201 Created):**

```json
{
  "id": "59728027-e986-4827-a925-189978d77d07",
  "code": "PROMO1",
  "description": "Cupom de desconto de verão",
  "discountValue": 15.00,
  "createdAt": "2024-01-01T10:00:00",
  "expirationDate": "2025-12-31T23:59:59",
  "deletedAt": null,
  "status": "ACTIVE",
  "published": true,
  "redeemed": false
}
```

### 2. Detalhar Cupom

Busca os detalhes de um cupom específico pelo seu ID.

*   **URL:** `/ecommerce/api/cupon/{id}`
*   **Método:** `GET`

**Retorno Esperado (200 OK):**

O retorno segue a mesma estrutura do objeto de resposta da criação (ver acima).

### 3. Deletar Cupom

Realiza a exclusão lógica (soft delete) de um cupom. O status do cupom é alterado para `DELETED` e a data de exclusão é preenchida.

*   **URL:** `/ecommerce/api/cupon/{id}`
*   **Método:** `DELETE`

**Retorno Esperado (204 No Content):**

Não há corpo de resposta.

---

## Swagger UI

A documentação interativa (OpenAPI) pode ser acessada em:
`http://localhost:8080/ecommerce/api/swagger-ui/index.html`