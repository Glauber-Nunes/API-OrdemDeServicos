package com.gnsoftware.Ordem.Servico.repository;

import com.gnsoftware.Ordem.Servico.model.StatusOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOrdemServicoRepository extends JpaRepository<StatusOrdemServico, Long> {
}