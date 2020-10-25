package com.controleponto.api.domain.repository;

import com.controleponto.api.domain.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lancamentos extends JpaRepository<Lancamento, Integer> {
}
