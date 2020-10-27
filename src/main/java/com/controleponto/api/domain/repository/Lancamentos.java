package com.controleponto.api.domain.repository;

import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Lancamentos extends JpaRepository<Lancamento, Integer> {

    List<Lancamento> findByFuncionarioAndCriacao(Funcionario funcionario, LocalDate criacao);

    List<Lancamento> findByFuncionarioAndCriacaoBetween(Funcionario funcionario, LocalDate inicio, LocalDate fim);

}
