package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "projeto")
@Getter @Setter
public class Projeto extends EntidadeBase {

    private String nome;
    private String descricao;
    private boolean ativo;

    @PrePersist
    private void prePersist() {
        ativo = true;
    }

}
