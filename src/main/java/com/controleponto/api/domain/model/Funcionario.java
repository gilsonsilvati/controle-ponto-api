package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "funcionario", uniqueConstraints = { @UniqueConstraint(name = "unq_email", columnNames = "email"), @UniqueConstraint(name = "unq_cpf", columnNames = "cpf") },
        indexes = { @Index(name = "idx_email", columnList = "email"), @Index(name = "idx_cpf", columnList = "cpf") })
@Getter @Setter
public class Funcionario extends EntidadeBase {

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 11, nullable = false)
    private String cpf;

    @ManyToMany
    @JoinTable(name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "funcionario_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_funcionario_projeto_funcionario")),
            inverseJoinColumns = @JoinColumn(name = "projeto_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_funcionario_projeto_projeto")))
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "funcionario")
    private List<Lancamento> lancamentos;

}
