package com.gnsoftware.Ordem.Servico.config;

import com.gnsoftware.Ordem.Servico.model.StatusOrdemServico;
import com.gnsoftware.Ordem.Servico.repository.StatusOrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CargaStatusOrdemServicoBD implements CommandLineRunner {

    @Autowired
    private StatusOrdemServicoRepository statusOrdemServico;

    @Override
    public void run(String... args) throws Exception {

        StatusOrdemServico ABERTA = new StatusOrdemServico(null, "ABERTA");
        StatusOrdemServico ENCERRADA = new StatusOrdemServico(null, "ENCERRADA");

        //statusOrdemServico.saveAll(Arrays.asList(ABERTA, ENCERRADA));
    }
}
