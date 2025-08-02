package br.com.solicitacao.dto;


import java.math.BigDecimal;

public class SolicitacaoItemResponseDTO {

    private Long productId;
    private String nomeProduct;
    private String descricaoProduct;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private BigDecimal subtotal;

    public SolicitacaoItemResponseDTO() {}

    public SolicitacaoItemResponseDTO(Long productId, String nomeProduct, String descricaoProduct,
                                      BigDecimal precoUnitario, Integer quantidade, BigDecimal subtotal) {
        this.productId = productId;
        this.nomeProduct = nomeProduct;
        this.descricaoProduct = descricaoProduct;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNomeProduct() {
        return nomeProduct;
    }

    public void setNomeProduct(String nomeProduct) {
        this.nomeProduct = nomeProduct;
    }

    public String getDescricaoProduct() {
        return descricaoProduct;
    }

    public void setDescricaoProduct(String descricaoProduct) {
        this.descricaoProduct = descricaoProduct;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
