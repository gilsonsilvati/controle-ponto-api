package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import com.controleponto.api.domain.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.OffsetTime;

@Entity
@Getter @Setter
public class Lancamento extends EntidadeBase {

    private OffsetTime hora;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    private Funcionario funcionario;

}
