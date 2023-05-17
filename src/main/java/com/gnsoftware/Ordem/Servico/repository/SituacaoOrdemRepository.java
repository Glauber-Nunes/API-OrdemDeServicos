package com.gnsoftware.Ordem.Servico.repository;

import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoOrdemRepository extends JpaRepository<SituacaoOrdem, Long> {
}