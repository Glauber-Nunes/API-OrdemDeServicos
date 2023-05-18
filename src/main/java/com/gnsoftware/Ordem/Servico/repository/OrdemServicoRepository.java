package com.gnsoftware.Ordem.Servico.repository;

import com.gnsoftware.Ordem.Servico.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}