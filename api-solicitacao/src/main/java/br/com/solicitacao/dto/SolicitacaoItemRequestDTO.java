package br.com.solicitacao.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SolicitacaoItemRequestDTO {

    @NotNull(message = "ID do produto é obrigatório")
    private Long productId;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    public SolicitacaoItemRequestDTO() {}

    public SolicitacaoItemRequestDTO(Long productId, Integer quantidade) {
        this.productId = productId;
        this.quantidade = quantidade;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
