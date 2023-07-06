package com.gnsoftware.Ordem.Servico.config;

import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;
import com.gnsoftware.Ordem.Servico.repository.SituacaoOrdemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class CargaSituacaoOrdemBD implements CommandLineRunner {

    @Autowired
    private SituacaoOrdemRepository situacaoOrdemRepository;

    @Override
    public void run(String... args) throws Exception {

        SituacaoOrdem situacaoOrdem1 = new SituacaoOrdem(null, "AGUARDANDO");
        SituacaoOrdem situacaoOrdem2 = new SituacaoOrdem(null, "AGUARDANDO PEÇA");
        SituacaoOrdem situacaoOrdem3 = new SituacaoOrdem(null, "PRONTO");
        SituacaoOrdem situacaoOrdem4 = new SituacaoOrdem(null, "EM CONSERTO");

       // situacaoOrdemRepository.saveAll(Arrays.asList(situacaoOrdem1, situacaoOrdem2, situacaoOrdem3, situacaoOrdem4));
    }
}
