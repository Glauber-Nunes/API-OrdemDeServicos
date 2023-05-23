package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.*;
import com.gnsoftware.Ordem.Servico.repository.OSRepository;
import com.gnsoftware.Ordem.Servico.services.*;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class OSServiceImpl implements OSService {

    @Autowired
    private OSRepository OSRepository;
    @Autowired
    private AtendenteService atendenteService;
    @Autowired
    private SituacaoOrdemService situacaoOrdemService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private StatusOrdemServicoService statusOrdemServicoService;
    @Autowired
    private ServicoService servicoService;

    @Override
    public OS save(OSForm osForm) {

        Atendente atendente = atendenteService.findById(osForm.getAtendente_id());
        SituacaoOrdem situacaoOrdem = situacaoOrdemService.findById(osForm.getSituacaoOrdem_id());
        Cliente cliente = clienteService.findById(osForm.getCliente_id());
        Tecnico tecnico = tecnicoService.fidById(osForm.getTecnico_id());
        Produto produto = produtoService.findById(osForm.getProduto_id());
        Servico servico = servicoService.findById(osForm.getServico_id());
        StatusOrdemServico statusOrdemServico = statusOrdemServicoService.findById(osForm.getStatusOrdemServico_id());

        return OSRepository.save(
                OS.builder()
                        .id(null)
                        .atendente(atendente)
                        .situacaoOrdem(situacaoOrdem)
                        .cliente(cliente)
                        .descricao(osForm.getDescricao())
                        .tecnico(tecnico)
                        .DataDoServico(Instant.now())
                        .quantidade(osForm.getQuantidade())
                        .produto(produto)
                        .servico(servico)
                        .observacoes(osForm.getObservacoes())
                        .valorTotalOrdem(osForm.calculaValorTotalOrdemServico(servico, produto))
                        .statusOrdemServico(statusOrdemServico)
                        .build()
        );
    }

    @Override
    public OS update(Long id, OSForm OSForm) {
        return null;
    }

    @Override
    public OS findById(Long id) {
        Optional<OS> ordemServico = OSRepository.findById(id);

        return ordemServico.orElseThrow(() -> new ModelNotFound("Ordem De Serviço Não Encontrada"));
    }

    @Override
    public OS delete(Long id) {
        return null;
    }
}
