package com.controleponto.api.modelmapper;

import com.controleponto.api.domain.model.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LancamentoModel {

    private LocalTime hora;
    private String descricao;
    private Tipo tipo;
    private FuncionarioModel funcionario;

}
