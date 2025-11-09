# 🍞 Sistema de Pedidos para Padaria (API Back-end)

Este repositório contém o código-fonte da API REST para um Sistema de Gerenciamento de Pedidos de uma padaria. O projeto é construído com **Java 17** e **Spring Boot**.

O sistema permite que clientes se cadastrem, façam pedidos online, e que a equipe interna (Atendentes e Administradores) gerencie o cardápio, o estoque e o fluxo de processamento dos pedidos.

> 🚧 **Status do Projeto:** Em Desenvolvimento 🚧

---

## 🚀 Funcionalidades Implementadas (Casos de Uso)

Este projeto foi modelado para cobrir os seguintes casos de uso:

### 👤 Cliente
* **Fazer Cadastro:** Criar uma nova conta de cliente.
* **Fazer Login:** Autenticar-se no sistema (com senha criptografada).
* **Gerenciar Endereço:** Adicionar ou atualizar seu endereço de entrega.
* **Fazer Pedido:** Montar um carrinho e submeter um novo pedido.
* **Acompanhar Pedido:** Ver o status atual de seus pedidos.

### 🧑‍🍳 Atendente
* **Fazer Login (Funcionário):** Acessar o sistema com credenciais de funcionário.
* **Acompanhar Fila de Pedidos:** Ver todos os pedidos com status `PENDENTE`.
* **Processar Pedido:** Atualizar o status de um pedido (ex: `EM_PREPARO`, `PRONTO_PARA_ENTREGA`).

### 🛠️ Administrador
* **Fazer Login (Admin):** Acessar o sistema com credenciais de administrador.
* **Gerenciar Cardápio (Produtos):** Criar, editar e desativar produtos do cardápio.
* **Gerenciar Estoque:** Atualizar a `quantidadeEstoque` dos produtos.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
* **Spring Security:** Para autenticação (login) e autorização (permissões).
* **MariaDB (ou MySQL):** Como banco de dados relacional.
* **Maven:** Para gerenciamento de dependências.
* **Lombok:** Para reduzir código boilerplate (Getters, Setters, Construtores).
* **Swagger (OpenAPI 3):** Para documentação e teste interativo da API.

---

## 🗃️ Modelo de Dados (Entidades)

O banco de dados foi modelado com as seguintes entidades JPA:

* **`Cliente`**: Armazena os dados pessoais do cliente, incluindo seu e-mail e senha (`@OneToOne` com `Endereco`).
* **`Endereco`**: Armazena os dados de endereço de entrega, ligado a um `Cliente`.
* **`Funcionario`**: Classe base abstrata que usa herança (`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`) para definir:
    * **`Atendente`**
    * **`Administrador`**
* **`Produto`**: Define um item do cardápio, incluindo `preco`, `CategoriaProduto` (Enum) e `quantidadeEstoque`.
* **`Pedido`**: A entidade central, ligada ao `Cliente` e ao `EnderecoEntrega`, contendo um `StatusPedido` (Enum) e o `valorTotal`.
* **`ItemPedido`**: A "linha" do pedido, ligando um `Pedido` a um `Produto` e armazenando a `quantidade` e o `precoUnitario` (para histórico).

---

## 📚 Documentação da API (Swagger)

A documentação interativa de todos os endpoints da API está disponível via Swagger UI.

1.  Rode a aplicação localmente.
2.  Acesse o seguinte link no seu navegador:
    [**http://localhost:8080/docs**](http://localhost:8080/docs)

### Principais Controllers Disponíveis:

* **`ClienteController`**
    * `POST /api/clientes/register`: Cria um novo cliente.
    * `POST /api/clientes/login`: (Endpoint para login personalizado).
* **`EnderecoController`**
    * `POST /api/clientes/{clienteId}/endereco`: Adiciona/atualiza o endereço de um cliente.
    * `GET /api/clientes/{clienteId}/endereco`: Busca o endereço de um cliente.
* **`ProdutoController`**
    * `GET /api/produtos`: Lista todos os produtos ativos (cardápio).
    * `POST /api/produtos`: Cria um novo produto (Requer ADM).
    * `PUT /api/produtos/{id}`: Atualiza um produto (Requer ADM).
    * `DELETE /api/produtos/{id}`: Desativa um produto (Requer ADM).
* **`PedidoController`**
    * `POST /api/pedidos`: Cria um novo pedido (Implementa verificação de estoque).
    * `GET /api/pedidos/cliente/{clienteId}`: Lista todos os pedidos de um cliente.
    * `GET /api/pedidos/fila`: Busca pedidos por status (Ex: `PENDENTE`).
    * `PUT /api/pedidos/{pedidoId}/status`: Atualiza o status de um pedido.

---

## 🔒 Configuração de Segurança

A segurança é gerenciada pelo **Spring Security**.

1.  **Criptografia:** Senhas de clientes e funcionários são automaticamente criptografadas usando `BCryptPasswordEncoder` no momento do cadastro.
2.  **CORS:** O sistema está configurado para aceitar requisições de diferentes origens (`*`), permitindo que o Swagger e futuras aplicações front-end funcionem sem erros de `Failed to fetch`.
3.  **Autorização (Modo de Teste):** Atualmente, para facilitar o desenvolvimento e os testes via Swagger, a segurança está configurada para permitir todas as requisições:
    ```java
    .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/**").permitAll()
    )
    ```
    *O próximo passo deste projeto é reimplementar a segurança baseada em papéis (Roles) para proteger os endpoints de ADM e Atendente.*

---

## 🏁 Como Executar Localmente

1.  **Clone o repositório:**
    ```bash
    git clone [URL-DO-SEU-REPOSITORIO]
    ```
2.  **Configure o Banco de Dados:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Configure as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com os dados do seu banco MariaDB/MySQL.
    * Certifique-se que o Spring está configurado para rodar os scripts (`spring.jpa.hibernate.ddl-auto` deve ser `create`, `create-drop` ou `update` para o `data.sql` rodar).
3.  **Execute a Aplicação:**
    * Rode a aplicação através da sua IDE (IntelliJ/VSCode) ou via Maven:
    ```bash
    mvn spring-boot:run
    ```
4.  **Teste:**
    * Acesse o Swagger em `http://localhost:8080/docs` para testar os endpoints.

---

## 📜 Scripts de Carga (SQL)

Para auxiliar nos testes, você pode criar um arquivo chamado `data.sql` na pasta `src/main/resources/`. O Spring Boot o executará na inicialização e populará o banco com os dados abaixo.

**Senhas dos Funcionários (já criptografadas):**
* **ADM (Carla):** `senhaAdm`
* **Atendente (Bruno):** `senhaAten`

```sql
/* data.sql - Coloque este arquivo em src/main/resources/ */

-- 1. ADICIONA OS FUNCIONÁRIOS
-- Senha para 'senhaAdm' (criptografada)
INSERT INTO Funcionario (tipo_funcionario, nome, email, senha) 
VALUES ('ADMINISTRADOR', 'Carla Administradora', 'carla.adm@padaria.com', '$2a$10$f/A.e.3.i9i/9qI9s0.pX.eT.U.s2.h/u.s.9eT.W.G');

-- Senha para 'senhaAten' (criptografada)
INSERT INTO Funcionario (tipo_funcionario, nome, email, senha) 
VALUES ('ATENDENTE', 'Bruno Atendente', 'bruno.atendente@padaria.com', '$2a$10$s/H.m/C.y.e/j.E.t.e/q.u/a.s.e/r.s/u.k/e.y');


-- 2. ADICIONA OS PRODUTOS (CARDÁPIO)
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) 
VALUES ('Pão Francês', 'Unidade de pão francês fresco', 0.50, 'PADARIA', 200, TRUE);

INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) 
VALUES ('Bolo de Chocolate', 'Fatia de bolo de chocolate com cobertura', 25.00, 'CONFEITARIA', 10, TRUE);

INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) 
VALUES ('Coca-Cola 2L', 'Refrigerante Coca-Cola 2L', 10.00, 'BEBIDAS', 0, TRUE); -- SEM ESTOQUE

INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) 
VALUES ('Queijo Minas', 'Peça de Queijo Minas Frescal', 15.00, 'FRIOS', 50, TRUE);
