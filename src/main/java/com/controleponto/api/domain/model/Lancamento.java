package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import com.controleponto.api.domain.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetTime;

@Entity
@Table(name = "lancamento")
@Getter @Setter
public class Lancamento extends EntidadeBase {

    @Column(nullable = false)
    private OffsetTime hora;
    private String descricao;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    private Funcionario funcionario;

}
