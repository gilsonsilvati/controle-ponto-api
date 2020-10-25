CREATE TABLE projeto (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    criacao DATE,
    atualizacao DATE,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR
);

CREATE INDEX idx_nome ON projeto (nome);
