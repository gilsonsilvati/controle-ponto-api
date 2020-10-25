CREATE TABLE lancamento (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    criacao DATE,
    atualizacao DATE,
    hora TIME NOT NULL,
    descricao VARCHAR,
    tipo VARCHAR(20) NOT NULL,
    funcionario_id INTEGER NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(id)
);
