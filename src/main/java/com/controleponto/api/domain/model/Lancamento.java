package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import com.controleponto.api.domain.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "lancamento")
@Getter @Setter
public class Lancamento extends EntidadeBase {

    private LocalTime hora;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    private Funcionario funcionario;

}
