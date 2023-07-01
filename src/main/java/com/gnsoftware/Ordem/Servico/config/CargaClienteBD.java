package com.gnsoftware.Ordem.Servico.config;

import com.gnsoftware.Ordem.Servico.model.Atendente;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.repository.AtendenteRepository;
import com.gnsoftware.Ordem.Servico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class CargaClienteBD implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AtendenteRepository atendenteRepository;

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = new Cliente(null, "felipe", "155.695.074-85", "7878877", "819189652", "felipe4@gmail.com", "rua 2");
        Cliente cliente1 = new Cliente(null, "joao", "262.966.600-06", "4545454", "81565656", "joao@gmail.com", "rua 3");
        Cliente cliente2 = new Cliente(null, "rafael", "319.386.750-02", "1545454", "81963548", "rafael@gmail.com", "rua 4");

       // clienteRepository.saveAll(Arrays.asList(cliente, cliente1, cliente2));

        Atendente atendente = new Atendente(null, "Felipe", "415.933.010-06");
        Atendente atendente2 = new Atendente(null, "rafaela", "215.430.700-00");
        //atendenteRepository.save(atendente);
        //atendenteRepository.save(atendente2);
    }
}
