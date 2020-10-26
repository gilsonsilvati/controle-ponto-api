package com.controleponto.api.repository;

import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.repository.Funcionarios;
import com.controleponto.api.util.MassaDeDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FuncionariosTest extends MassaDeDados {

    @Autowired
    private Funcionarios funcionarios;

    @Test
    public void deve_buscar_pelo_cpf() {
        Optional<Funcionario> funcionarioOptional = funcionarios.findByCpf(cpf);
        Assertions.assertTrue(funcionarioOptional.isPresent());
    }

    @Test
    public void deve_buscar_pelo_email() {
        Optional<Funcionario> funcionarioOptional = funcionarios.findByEmailIgnoreCase(email);
        Assertions.assertTrue(funcionarioOptional.isPresent());
    }

    @Test
    public void deve_buscar_pelo_email_ou_cpf() {
        Optional<Funcionario> funcionarioOptional = funcionarios.findByEmailIgnoreCaseOrCpf(email, null);
        Optional<Funcionario> funcionarioOptional1 = funcionarios.findByEmailIgnoreCaseOrCpf(null, cpf);

        Assertions.assertTrue(funcionarioOptional.isPresent());
        Assertions.assertTrue(funcionarioOptional1.isPresent());
    }

    @Test
    public void nao_deve_retornar_nada() {
        Optional<Funcionario> funcionarioOptional = funcionarios.findByCpf(cpf_invalido);
        Optional<Funcionario> funcionarioOptional1 = funcionarios.findByEmailIgnoreCase(email_invalido);

        Assertions.assertFalse(funcionarioOptional.isPresent());
        Assertions.assertFalse(funcionarioOptional1.isPresent());
    }

}
