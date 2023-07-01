package com.gnsoftware.Ordem.Servico.repository;

import com.gnsoftware.Ordem.Servico.dto.ClienteForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(@Param("cpf") String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByemail(String email);
}