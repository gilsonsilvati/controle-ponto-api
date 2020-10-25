package com.controleponto.api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario_projeto")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FuncionarioProjeto {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private FuncionarioProjetoId id;

}
