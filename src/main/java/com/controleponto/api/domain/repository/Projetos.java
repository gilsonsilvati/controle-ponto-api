package com.controleponto.api.domain.repository;

import com.controleponto.api.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Projetos extends JpaRepository<Projeto, Integer> {

    Optional<Projeto> findByNomeIgnoreCaseAndAtivo(String nome, boolean ativo);

}
