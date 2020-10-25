package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projeto", indexes = { @Index(name = "idx_nome", columnList = "nome") })
@Getter @Setter
public class Projeto extends EntidadeBase {

    @Column(length = 100, nullable = false)
    private String nome;
    private String descricao;
    private boolean ativo;

    @PrePersist
    private void prePersist() {
        ativo = true;
    }

}
