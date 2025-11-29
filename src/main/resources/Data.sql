/* =================================================================
 SCRIPT DE DADOS FICTÍCIOS - PADARIA CIDADE
 VERSÃO ATUALIZADA (CORRIGIDA)
 - Nomes de tabelas em 'snake_case' (ex: item_pedido)
   para bater com o que o Hibernate/JPA cria.
=================================================================
*/

/* BLOCO 1: FUNCIONÁRIOS (Tabela: funcionario) */
INSERT INTO funcionario (tipo_funcionario, nome, email, senha) VALUES
                                                                   ('ADMINISTRADOR', 'Sônia Gerente', 'sonia.gerente@padaria.com', 'senhaAdm'),
                                                                   ('ATENDENTE', 'Ana Atendente', 'ana.atendente@padaria.com', 'senhaAten'),
                                                                   ('ATENDENTE', 'Bruno Atendente', 'bruno.atendente@padaria.com', 'senhaAten');

/* BLOCO 2: PRODUTOS (Tabela: produto) */
INSERT INTO produto (nome, descricao, preco, categoria, quantidade_estoque, ativo) VALUES
                                                                                       ('Pastel de Carne', 'Pastel frito recheado com carne moída temperada', 6.50, 'PADARIA', 50, TRUE),
                                                                                       ('Pastel de Queijo', 'Pastel frito recheado com queijo minas', 6.50, 'PADARIA', 40, TRUE),
                                                                                       ('Coxinha de Frango', 'Coxinha tradicional de frango com catupiry', 7.00, 'PADARIA', 60, TRUE),
                                                                                       ('Pão de Queijo', 'Unidade de pão de queijo mineiro', 3.50, 'PADARIA', 100, TRUE),
                                                                                       ('Café Expresso', 'Café expresso 50ml', 4.00, 'BEBIDAS', 999, TRUE),
                                                                                       ('Misto Quente', 'Pão de forma na chapa com presunto e queijo. (Feito na hora)', 8.00, 'PADARIA', 999, TRUE),
                                                                                       ('Enrolado de Presunto', 'Salgado assado com presunto e queijo', 6.00, 'PADARIA', 30, TRUE),
                                                                                       ('Refrigerante Lata', 'Lata 350ml (Coca, Guaraná, etc)', 5.50, 'BEBIDAS', 80, TRUE),
                                                                                       ('Cigarro (Maço)', 'Vendido separadamente no caixa. (Item de Mercearia)', 12.00, 'MERCEARIA', 50, TRUE),
                                                                                       ('Refrigerante 2L (Teste)', 'Refrigerante 2L para teste de estoque', 10.00, 'BEBIDAS', 0, TRUE);

/* BLOCO 3: CLIENTES (Tabela: cliente) */
INSERT INTO cliente (nome, sobrenome, cpf, email, senha, telefone, data_nascimento) VALUES
                                                                                        ('Marcos', 'Oliveira', '111.111.111-11', 'marcos.trabalho@email.com', 'cliente123', '31999991111', '1985-10-20'),
                                                                                        ('Helena', 'Gomes', '222.222.222-22', 'helena.gomes@email.com', 'cliente123', '31999992222', '1952-04-15'),
                                                                                        ('Juliana', 'Souza', '333.333.333-33', 'juliana.souza@email.com', 'cliente123', '31999993333', '1998-07-30');

/* BLOCO 4: ENDEREÇOS (Tabela: endereco) */
INSERT INTO endereco (logradouro, numero, bairro, cidade, estado, cep, cliente_id) VALUES
                                                                                       ('Rua dos Aimorés', '1500', 'Centro', 'Belo Horizonte', 'MG', '30140-071', 1),
                                                                                       ('Avenida Brasil', '800', 'Savassi', 'Belo Horizonte', 'MG', '30140-001', 2);

/* BLOCO 5: PEDIDOS DE EXEMPLO (Tabela: pedido) */
INSERT INTO pedido (cliente_id, endereco_id, status, data_criacao, valor_total, forma_pagamento) VALUES
                                                                                                     (1, 1, 'ENTREGUE', '2025-11-08T09:30:00', 7.50, 'PIX'),
                                                                                                     (2, 2, 'PENDENTE', '2025-11-09T10:00:00', 19.50, 'DINHEIRO');

/* BLOCO 6: ITENS DO PEDIDO (Tabela: item_pedido) - CORRIGIDO */
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
                                                                                (1, 4, 1, 3.50), -- Pedido 1 (Marcos) Pão de Queijo
                                                                                (1, 5, 1, 4.00); -- Pedido 1 (Marcos) Café

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
                                                                                (2, 3, 2, 7.00), -- Pedido 2 (Helena) Coxinhas
                                                                                (2, 8, 1, 5.50); -- Pedido 2 (Helena) Refrigerante