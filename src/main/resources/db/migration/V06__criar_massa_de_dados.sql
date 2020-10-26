INSERT INTO projeto (nome, ativo) VALUES ('Teste Teste', true);
INSERT INTO projeto (nome, ativo) VALUES ('Controle Ponto', true);
INSERT INTO projeto (nome, ativo) VALUES ('Controle Ponto API', true);

INSERT INTO funcionario (nome, email, cpf) VALUES ('Gilson Show', 'gilson.show@gmail.com', '12312312387');
INSERT INTO funcionario (nome, email, cpf) VALUES ('Arthur Show', 'arthur.show@gmail.com', '55723606190');
INSERT INTO funcionario (nome, email, cpf) VALUES ('Igor Show', 'igor.show@gmail.com', '83142693440');
INSERT INTO funcionario (nome, email, cpf) VALUES ('Bárbara Show', 'barbara.show@gmail.com', '64899874499');

INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('09:00:00', 'INICIO_TRABALHO', 1, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('13:00:00', 'INICIO_ALMOCO', 1, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('14:00:00', 'TERMINO_ALMOCO', 1, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('18:00:00', 'TERMINO_TRABALHO', 1, SYSDATE());

INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('09:00:00', 'INICIO_TRABALHO', 2, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('13:00:00', 'INICIO_ALMOCO', 2, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('13:50:00', 'TERMINO_ALMOCO', 2, SYSDATE());
INSERT INTO lancamento (hora, tipo, funcionario_id, criacao) VALUES ('18:00:00', 'TERMINO_TRABALHO', 2, SYSDATE());
