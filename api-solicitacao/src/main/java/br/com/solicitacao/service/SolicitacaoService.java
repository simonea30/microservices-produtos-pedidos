package br.com.solicitacao.service;

import br.com.solicitacao.cliente.CatalogServiceClient;
import br.com.solicitacao.dto.*;
import br.com.solicitacao.exception.ProductNotAvailableException;
import br.com.solicitacao.model.Solicitacao;
import br.com.solicitacao.model.SolicitacaoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    private CatalogServiceClient _catalogServiceClient;

    // Simulação de armazenamento em memória (sem persistência)
    private final Map<String, Solicitacao> solicitacoes = new ConcurrentHashMap<>();

    public SolicitacaoResponseDTO createOrder(SolicitacaoRequestDTO orderRequest) {
        // Validar se todos os produtos existem e estão disponíveis
        List<SolicitacaoItem> solicitacoesItems = orderRequest.getItens().stream()
                .map(this::createOrderItem)
                .collect(Collectors.toList());

        // Calcular valor total
        BigDecimal valorTotal = solicitacoesItems.stream()
                .map(SolicitacaoItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Criar pedido
        String orderId = UUID.randomUUID().toString();
        Solicitacao solicitacao = new Solicitacao(
                orderId,
                orderRequest.getNomeCliente(),
                orderRequest.getEndereco(),
                solicitacoesItems,
                valorTotal,
                "CRIADO");

        // Armazenar em memória
        solicitacoes.put(orderId, solicitacao);

        return convertToResponseDTO(solicitacao);
    }

    public SolicitacaoResponseDTO getOrderById(String id) {
        Solicitacao solicitacao = solicitacoes.get(id);
        if (solicitacao == null) {
            throw new ProductNotAvailableException("Pedido não encontrado com ID: " + id);
        }
        return convertToResponseDTO(solicitacao);
    }

    public List<SolicitacaoResponseDTO> getAllOrders() {
        return solicitacoes.values().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private SolicitacaoItem createOrderItem(SolicitacaoItemRequestDTO itemRequest) {
        try {
            ProdutoDTO product = _catalogServiceClient.pegarProdutoId(itemRequest.getProductId());

            return new SolicitacaoItem(
                    product.getId(),
                    product.getNome(),
                    product.getDescricao(),
                    product.getPreco(),
                    itemRequest.getQuantidade());
        } catch (Exception e) {
            throw new ProductNotAvailableException(
                    "Produto não disponível ou não encontrado: " + itemRequest.getProductId());
        }
    }

    private SolicitacaoResponseDTO convertToResponseDTO(Solicitacao solicitacao) {
        List<SolicitacaoItemResponseDTO> itemsDTO = solicitacao.getItens().stream()
                .map(this::convertToItemResponseDTO)
                .collect(Collectors.toList());

        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                solicitacao.getNomeCliente(),
                solicitacao.getEndereco(),
                itemsDTO,
                solicitacao.getValorTotal(),
                solicitacao.getStatus(),
                solicitacao.getDataHoraCriacao());
    }

    private SolicitacaoItemResponseDTO convertToItemResponseDTO(SolicitacaoItem item) {
        return new SolicitacaoItemResponseDTO(
                item.getProductId(),
                item.getNomeProduct(),
                item.getDescricaoProduct(),
                item.getPrecoUnitario(),
                item.getQuantidade(),
                item.getSubtotal());
    }
}
