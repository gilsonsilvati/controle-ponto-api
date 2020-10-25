CREATE TABLE funcionario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    criacao DATE,
    atualizacao DATE,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL
);

CREATE INDEX idx_email ON funcionario (email);
CREATE INDEX idx_cpf ON funcionario (cpf);
