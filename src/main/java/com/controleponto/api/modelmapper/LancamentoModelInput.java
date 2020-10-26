package com.controleponto.api.modelmapper;

import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.model.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LancamentoModelInput {

    @NotNull(message = "Horário é obrigatório")
    private LocalTime hora;

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    private String cpf;
    private Tipo tipo;
    private String descricao;
    private Funcionario funcionario;

}
