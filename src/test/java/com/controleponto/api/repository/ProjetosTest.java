package com.controleponto.api.repository;

import com.controleponto.api.domain.model.Projeto;
import com.controleponto.api.domain.repository.Projetos;
import com.controleponto.api.util.MassaDeDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProjetosTest extends MassaDeDados {

    @Autowired
    private Projetos projetos;

    @Test
    public void deve_buscar_pelo_nome_ativo() {
        Optional<Projeto> projetoOptional = projetos.findByNomeIgnoreCaseAndAtivo(nome, true);
        Assertions.assertEquals(nome, projetoOptional.get().getNome());
    }

    @Test
    public void nao_deve_retornar_nada_pelo_nome_ativo() {
        Optional<Projeto> projetoOptional = projetos.findByNomeIgnoreCaseAndAtivo(nome_invalido, true);
        Assertions.assertTrue(projetoOptional.isEmpty());
    }

}
