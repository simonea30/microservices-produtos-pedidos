package br.com.solicitacao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoResponseDTO {

    private String id;
    private String nomeCliente;
    private String endereco;
    private List<SolicitacaoItemResponseDTO> itens;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataHoraCriacao;

    public SolicitacaoResponseDTO() {}

    public SolicitacaoResponseDTO(String id, String nomeCliente, String endereco,
                                  List<SolicitacaoItemResponseDTO> itens, BigDecimal valorTotal,
                                  String status, LocalDateTime dataHoraCriacao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<SolicitacaoItemResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<SolicitacaoItemResponseDTO> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
