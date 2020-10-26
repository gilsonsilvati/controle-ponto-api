package com.controleponto.api.repository;

import com.controleponto.api.domain.exception.EntidadeNaoEncontradaException;
import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.model.Lancamento;
import com.controleponto.api.domain.repository.Funcionarios;
import com.controleponto.api.domain.repository.Lancamentos;
import com.controleponto.api.util.MassaDeDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class LancamentosTest extends MassaDeDados {

    @Autowired
    private Lancamentos lancamentos;

    @Autowired
    private Funcionarios funcionarios;

    private Funcionario funcionario;

    @BeforeEach
    public void setUp() {
        funcionario = funcionarios.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não localizado."));
    }

    @Test
    public void deve_buscar_pelo_funcionario_por_data() throws EntidadeNaoEncontradaException {
        List<Lancamento> list = lancamentos.findByFuncionarioAndCriacao(funcionario, LocalDate.now());
        Assertions.assertFalse(list.isEmpty());
    }

}
