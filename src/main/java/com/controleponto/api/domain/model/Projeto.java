package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projeto", uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = "nome") },
        indexes = { @Index(name = "idx_nome", columnList = "nome") })
@Getter @Setter
public class Projeto extends EntidadeBase {

    @Column(length = 100, nullable = false)
    private String nome;
    private String descricao;

    @OneToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_projeto_funcionario"))
    private Funcionario responsavel;

    @ManyToMany(mappedBy = "projeto")
    private List<Funcionario> funcionarios;

}
