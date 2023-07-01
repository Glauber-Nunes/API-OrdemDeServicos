package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.controller.OSController;
import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.*;
import com.gnsoftware.Ordem.Servico.repository.OSRepository;
import com.gnsoftware.Ordem.Servico.services.*;
import com.gnsoftware.Ordem.Servico.services.exceptions.DataIntegrityViolationException;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
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

    @Autowired
    private FornecedorService fornecedorService;


    @Override
    public OS save(OSForm osForm) {

        Atendente atendente = atendenteService.findById(osForm.getAtendente_id());
        SituacaoOrdem situacaoOrdem = situacaoOrdemService.findById(osForm.getSituacaoOrdem_id());
        Cliente cliente = clienteService.findById(osForm.getCliente_id());
        Tecnico tecnico = tecnicoService.findById(osForm.getTecnico_id());
        Produto produto = produtoService.findById(osForm.getProduto_id());
        Fornecedor fornecedor = fornecedorService.findById(osForm.getFornecedor_id());
        Servico servico = servicoService.findById(osForm.getServico_id());
        StatusOrdemServico statusOrdemServico = statusOrdemServicoService.findById(1L); // recebe o status de aberto


        OS ordemDeServico = OS.builder()
                .id(null)
                .atendente(atendente)
                .situacaoOrdem(situacaoOrdem)
                .cliente(cliente)
                .descricao(osForm.getDescricao())
                .tecnico(tecnico)
                .DataDoServico(Instant.now())
                .quantidade(osForm.getQuantidade())
                .servico(servico)
                .produto(produto)
                .fornecedor(fornecedor)
                .observacoes(osForm.getObservacoes())
                .statusOrdemServico(statusOrdemServico)
                .valorTotalOrdem(osForm.calculaValorTotalOrdemServico(servico, produto))
                .build();

        return OSRepository.save(ordemDeServico);
    }

    @Override
    public OS update(Long id, OSForm osForm) {

        OS ordemservicoBanco = this.findById(id);

        Atendente atendente = atendenteService.findById(osForm.getAtendente_id());
        SituacaoOrdem situacaoOrdem = situacaoOrdemService.findById(osForm.getSituacaoOrdem_id());
        Cliente cliente = clienteService.findById(osForm.getCliente_id());
        Tecnico tecnico = tecnicoService.findById(osForm.getTecnico_id());
        Produto produto = produtoService.findById(osForm.getProduto_id());
        Fornecedor fornecedor = fornecedorService.findById(osForm.getFornecedor_id());
        Servico servico = servicoService.findById(osForm.getServico_id());
        StatusOrdemServico statusOrdemServico = statusOrdemServicoService.findById(osForm.getStatusOrdemServico_id());


        OS osUpdate = OS.builder()
                .id(ordemservicoBanco.getId())
                .atendente(atendente != null ? atendente : ordemservicoBanco.getAtendente())
                .situacaoOrdem(situacaoOrdem != null ? situacaoOrdem : ordemservicoBanco.getSituacaoOrdem())
                .cliente(cliente != null ? cliente : ordemservicoBanco.getCliente())
                .descricao(osForm.getDescricao() != null ? osForm.getDescricao() : ordemservicoBanco.getDescricao())
                .tecnico(tecnico != null ? tecnico : ordemservicoBanco.getTecnico())
                .DataDoServico(ordemservicoBanco.getDataDoServico())
                .quantidade(osForm.getQuantidade() != null ? osForm.getQuantidade() : ordemservicoBanco.getQuantidade())
                .servico(servico != null ? servico : ordemservicoBanco.getServico())
                .produto(produto != null ? produto : ordemservicoBanco.getProduto())
                .fornecedor(fornecedor != null ? fornecedor : ordemservicoBanco.getFornecedor())
                .observacoes(osForm.getObservacoes() != null ? osForm.getObservacoes() : ordemservicoBanco.getObservacoes())
                .statusOrdemServico(statusOrdemServico != null ? statusOrdemServico : ordemservicoBanco.getStatusOrdemServico())
                .valorTotalOrdem(osForm.calculaValorTotalOrdemServico(servico, produto))
                .build();

        return OSRepository.saveAndFlush(osUpdate);

    }

    @Override
    public OS findById(Long id) {
        Optional<OS> ordemServico = OSRepository.findById(id);

        return ordemServico.orElseThrow(() -> new ModelNotFound("Ordem De Serviço Não Encontrada"));
    }

    @Override
    public void delete(Long id) {
        OS os = this.findById(id);

        OSRepository.delete(os);
    }

    @Override
    public List<OS> findAll() {
        return OSRepository.findAll();
    }

    @Override
    public void finalizaServico(Long id_ordem_servico, OS os) {

        StatusOrdemServico statusENCERRADO = statusOrdemServicoService.findById(2L); //faz a bsuca automatica pelo status encerrado

        OS osBanco = this.findById(id_ordem_servico);

        if (osBanco.getStatusOrdemServico() == statusENCERRADO) {
            System.out.println("ENTROU AQUI");
            throw new DataIntegrityViolationException("ORDEM DE SERVIÇO JA ESTA ENCERRADA");

        } else {
            osBanco.setStatusOrdemServico(statusENCERRADO);
            OSRepository.saveAndFlush(osBanco);
        }


    }


}
