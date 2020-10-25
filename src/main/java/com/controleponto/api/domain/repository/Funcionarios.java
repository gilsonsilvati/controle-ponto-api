package com.controleponto.api.domain.repository;

import com.controleponto.api.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Funcionarios extends JpaRepository<Funcionario, Integer> {
}
