package com.controleponto.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class FuncionarioProjetoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Projeto projeto;

}
