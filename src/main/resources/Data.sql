/* =================================================================
 SCRIPT DE DADOS FICTÍCIOS - PADARIA CIDADE
 Baseado nas respostas da entrevista (Documento de Entrevista)
=================================================================
*/

/* =================================================================
 BLOCO 1: FUNCIONÁRIOS (Ref: Entrevista Bloco 2, Pergunta 1)
 1 Gerente e 10 Atendentes. Vamos cadastrar 1 Gerente e 2 Atendentes.
 Senhas em TEXTO PURO (normal).
=================================================================
*/
INSERT INTO Funcionario (tipo_funcionario, nome, email, senha) VALUES
                                                                   ('ADMINISTRADOR', 'Sônia Gerente', 'sonia.gerente@padaria.com', 'senhaAdm'), -- Senha normal
                                                                   ('ATENDENTE', 'Ana Atendente', 'ana.atendente@padaria.com', 'senhaAten'),     -- Senha normal
                                                                   ('ATENDENTE', 'Bruno Atendente', 'bruno.atendente@padaria.com', 'senhaAten');    -- Senha normal

/* =================================================================
 BLOCO 2: PRODUTOS (Ref: Entrevista Bloco 1, Pergunta 5)
 Cadastrando os produtos "mais vendidos".
 Coluna 'quantidade_estoque' (nome original).
=================================================================
*/
-- ID 1
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Pastel de Carne', 'Pastel frito recheado com carne moída temperada', 6.50, 'PADARIA', 50, TRUE);
-- ID 2
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Pastel de Queijo', 'Pastel frito recheado com queijo minas', 6.50, 'PADARIA', 40, TRUE);
-- ID 3
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Coxinha de Frango', 'Coxinha tradicional de frango com catupiry', 7.00, 'PADARIA', 60, TRUE);
-- ID 4
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Pão de Queijo', 'Unidade de pão de queijo mineiro', 3.50, 'PADARIA', 100, TRUE);
-- ID 5
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Café Expresso', 'Café expresso 50ml', 4.00, 'BEBIDAS', 999, TRUE);
-- ID 6
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Misto Quente', 'Pão de forma na chapa com presunto e queijo. (Feito na hora)', 8.00, 'PADARIA', 999, TRUE); -- Feito na hora
-- ID 7
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Enrolado de Presunto', 'Salgado assado com presunto e queijo', 6.00, 'PADARIA', 30, TRUE);
-- ID 8
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Refrigerante Lata', 'Lata 350ml (Coca, Guaraná, etc)', 5.50, 'BEBIDAS', 80, TRUE); -- Estoque é contado
-- ID 9
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Cigarro (Maço)', 'Vendido separadamente no caixa. (Item de Mercearia)', 12.00, 'MERCEARIA', 50, TRUE); -- Item de caixa
-- ID 10
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
    ('Refrigerante 2L (Teste)', 'Refrigerante 2L para teste de estoque', 10.00, 'BEBIDAS', 0, TRUE); -- Produto para teste de estoque

/* =================================================================
 BLOCO 3: CLIENTES (Ref: Entrevista Bloco 1, Q4, Q15, Q17)
 Senha em TEXTO PURO (normal): 'cliente123'
=================================================================
*/
-- ID 1 (Fiel, trabalha ao redor, pede sempre o mesmo)
INSERT INTO Cliente (nome, sobrenome, cpf, email, senha, telefone, data_nascimento) VALUES
    ('Marcos', 'Oliveira', '111.111.111-11', 'marcos.trabalho@email.com', 'cliente123', '31999991111', '1985-10-20');
-- ID 2 (Idosa, gosta de conversar)
INSERT INTO Cliente (nome, sobrenome, cpf, email, senha, telefone, data_nascimento) VALUES
    ('Helena', 'Gomes', '222.222.222-22', 'helena.gomes@email.com', 'cliente123', '31999992222', '1952-04-15');
-- ID 3 (Novo/Ocasional)
INSERT INTO Cliente (nome, sobrenome, cpf, email, senha, telefone, data_nascimento) VALUES
    ('Juliana', 'Souza', '333.333.333-33', 'juliana.souza@email.com', 'cliente123', '31999993333', '1998-07-30');

/* =================================================================
 BLOCO 4: ENDEREÇOS (Ref: Entrevista Bloco 1, Q1)
=================================================================
*/
-- Endereço do Marcos (ID 1)
INSERT INTO Endereco (logradouro, numero, bairro, cidade, estado, cep, cliente_id) VALUES
    ('Rua dos Aimorés', '1500', 'Centro', 'Belo Horizonte', 'MG', '30140-071', 1);
-- Endereço da Helena (ID 2)
INSERT INTO Endereco (logradouro, numero, bairro, cidade, estado, cep, cliente_id) VALUES
    ('Avenida Brasil', '800', 'Funcionários', 'Belo Horizonte', 'MG', '30140-001', 2);
-- Cliente 3 (Juliana) não tem endereço salvo.

/* =================================================================
 BLOCO 5: PEDIDOS DE EXEMPLO
=================================================================
*/
-- Pedido 1 (Marcos, o cliente fiel. Pede "sempre o mesmo": Pão de Queijo e Café)
INSERT INTO Pedido (cliente_id, endereco_id, status, data_criacao, valor_total) VALUES
    (1, 1, 'ENTREGUE', '2025-11-08T09:30:00', 7.50); -- Pedido de ontem

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
                                                                               (1, 4, 1, 3.50), -- Pão de Queijo (ID 4)
                                                                               (1, 5, 1, 4.00); -- Café Expresso (ID 5)

-- Pedido 2 (Helena, pedido de hoje para entrega, PENDENTE na fila)
INSERT INTO Pedido (cliente_id, endereco_id, status, data_criacao, valor_total) VALUES
    (2, 2, 'PENDENTE', '2025-11-09T10:00:00', 19.50); -- Pedido de hoje

INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
                                                                               (2, 3, 2, 7.00), -- 2 Coxinhas (ID 3)
                                                                               (2, 8, 1, 5.50); -- 1 Refrigerante Lata (ID 8)