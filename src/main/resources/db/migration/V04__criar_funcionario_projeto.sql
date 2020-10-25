CREATE TABLE funcionario_projeto (
    funcionario_id INTEGER NOT NULL,
    projeto_id INTEGER NOT NULL,
    PRIMARY KEY (funcionario_id, projeto_id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(id),
    FOREIGN KEY (projeto_id) REFERENCES projeto(id)
);
