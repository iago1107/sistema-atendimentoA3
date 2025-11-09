


INSERT INTO funcionario (tipo_funcionario, nome, email, senha)
VALUES ('ADMINISTRADOR', 'Carla Administradora', 'carla.adm@padaria.com', '$2a$10$f/A.e.3.i9i/9qI9s0.pX.eT.U.s2.h/u.s.9eT.W.G');


INSERT INTO funcionario (tipo_funcionario, nome, email, senha)
VALUES ('ATENDENTE', 'Bruno Atendente', 'bruno.atendente@padaria.com', '$2a$10$s/H.m/C.y.e/j.E.t.e/q.u/a.s.e/r.s/u.k/e.y');


-- ADICIONA OS PRODUTOS
INSERT INTO Produto (nome, descricao, preco, categoria, quantidade_estoque, ativo)
VALUES ('Pão Francês', 'Unidade de pão francês fresco', 0.50, 'PADARIA', 200, TRUE);

INSERT INTO produto (nome, descricao, preco, categoria, quantidade_estoque, ativo)
VALUES ('Bolo de Chocolate', 'Fatia de bolo de chocolate com cobertura', 25.00, 'CONFEITARIA', 10, TRUE);

INSERT INTO produto (nome, descricao, preco, categoria, quantidade_estoque, ativo)
VALUES ('Coca-Cola 2L', 'Refrigerante Coca-Cola 2L', 10.00, 'BEBIDAS', 0, TRUE); -- SEM ESTOQUE

INSERT INTO produto (nome, descricao, preco, categoria, quantidade_estoque, ativo)
VALUES ('Queijo Minas', 'Peça de Queijo Minas Frescal', 15.00, 'FRIOS', 50, TRUE);