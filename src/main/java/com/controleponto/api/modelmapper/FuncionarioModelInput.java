package com.controleponto.api.modelmapper;

import com.controleponto.api.domain.model.Projeto;
import com.controleponto.api.domain.model.validation.CPFUnico;
import com.controleponto.api.domain.model.validation.EmailUnico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FuncionarioModelInput {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email
    @EmailUnico
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    @CPFUnico
    private String cpf;

    private List<Projeto> projetos;

}
