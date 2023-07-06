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
import java.time.LocalDate;
import java.util.Date;
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

    @Autowired
    private EmailService emailService;

    @Override
    public OS save(OSForm osForm) {

        Atendente atendente = atendenteService.findById(Long.valueOf(osForm.getAtendente_id()));
        SituacaoOrdem situacaoOrdem = situacaoOrdemService.findById(Long.valueOf(osForm.getSituacaoOrdem_id()));
        Cliente cliente = clienteService.findById(Long.valueOf(osForm.getCliente_id()));
        Tecnico tecnico = tecnicoService.findById(Long.valueOf(osForm.getTecnico_id()));
        Produto produto = produtoService.findById(Long.valueOf(osForm.getProduto_id()));
        Fornecedor fornecedor = fornecedorService.findById(Long.valueOf(osForm.getFornecedor_id()));
        Servico servico = servicoService.findById(Long.valueOf(osForm.getServico_id()));
        StatusOrdemServico statusOrdemServico = statusOrdemServicoService.findById(1L); // recebe o status de aberto

        OS ordemDeServico = OS.builder()
                .id(null)
                .atendente(atendente)
                .situacaoOrdem(situacaoOrdem)
                .cliente(cliente)
                .descricao(osForm.getDescricao())
                .tecnico(tecnico)
                .DataDoServico(new Date())
                .quantidade(osForm.getQuantidade())
                .servico(servico)
                .produto(produto)
                .fornecedor(fornecedor)
                .observacoes(osForm.getObservacoes())
                .statusOrdemServico(statusOrdemServico)
                .valorTotalOrdem(this.calculaValorTotalOrdemServico(osForm, servico, produto))
                .build();

        OSRepository.save(ordemDeServico);
        emailService.enviarEmailOSAberta(cliente, osForm);

        return ordemDeServico;

    }

    @Override
    public OS update(Long id, OSForm osForm) {

        // this.consultaStatusOs(osForm); // verifica se o id da OS e encerrado

        OS ordemservicoBanco = this.findById(id);


        Atendente atendente = atendenteService.findById(osForm.getAtendente_id());
        SituacaoOrdem situacaoOrdem = situacaoOrdemService.findById(osForm.getSituacaoOrdem_id());
        Cliente cliente = clienteService.findById(osForm.getCliente_id());
        Tecnico tecnico = tecnicoService.findById(osForm.getTecnico_id());
        Produto produto = produtoService.findById(osForm.getProduto_id());
        Fornecedor fornecedor = fornecedorService.findById(osForm.getFornecedor_id());
        Servico servico = servicoService.findById(osForm.getServico_id());


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
                .statusOrdemServico(ordemservicoBanco.getStatusOrdemServico())
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
    public void finalizaOs(Long id_ordem_servico, OS os) {

        StatusOrdemServico statusENCERRADO = statusOrdemServicoService.findById(2L); //faz a bsuca automatica pelo status encerrado

        OS osBanco = this.findById(id_ordem_servico);

        if (osBanco.getStatusOrdemServico() == statusENCERRADO) {
            System.out.println("ENTROU AQUI");
            throw new DataIntegrityViolationException("ORDEM DE SERVIÇO JA ESTA ENCERRADA");

        } else {
            osBanco.setStatusOrdemServico(statusENCERRADO);
            osBanco.setDataFechamento(new Date()); // coloca a data do fechamento da OS assim que a os for finalizada pelo usuario
            OSRepository.saveAndFlush(osBanco);
            emailService.enviarEmailServicoFinalizado(os); // envia email serviço finalizado
        }


    }

    private Double calculaValorTotalOrdemServico(OSForm osForm, Servico servico, Produto produto) {

        if (osForm.getQuantidade() == null) {
            throw new DataIntegrityViolationException("Quantidade não pode ser nula");
        }

        Double servicoPreco = servico != null ? servico.getPreco() : 0.0; // se for diferente de nulo recebe o preço do salvamento se nao receba zero
        Double produtoPreco = produto != null ? produto.getPreco() : 0.0;

        osForm.setValorTotalOrdem((servicoPreco * osForm.getQuantidade()) + produtoPreco);
        return osForm.getValorTotalOrdem();
    }

    //CONSULTAR SE O ID DO STATUS DA OS É IGUAL A ENCERRADO, CASO FOR NÃO DEIXA O USUARIO EDITAR A OS
    private void consultaStatusOs(OSForm osForm) {

        StatusOrdemServico statusEncerrado = statusOrdemServicoService.findById(2L);

        if (osForm.getStatusOrdemServico_id() == 2L) {
            throw new DataIntegrityViolationException("VOÇE NÃO PODE EDITAR UMA OS FINALIZADA");
        }
    }


}
