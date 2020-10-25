package com.controleponto.api.modelmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FuncionarioModel {

    private String nome;
    private String email;
    private String cpf;
    private List<ProjetoModel> projetos;

}
