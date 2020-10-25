package com.controleponto.api.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ProjetoModelInput {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String descricao;
    private boolean ativo;

}
