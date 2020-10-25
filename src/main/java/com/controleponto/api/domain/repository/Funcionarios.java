package com.controleponto.api.domain.repository;

import com.controleponto.api.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Funcionarios extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByEmailIgnoreCase(String email);

    Optional<Funcionario> findByEmailIgnoreCaseOrCpf(String email, String cpf);

}
