package br.com.solicitacao.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderRequestDTO {

    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nomeCliente;

    @Size(max = 200, message = "Endereço não pode ter mais de 200 caracteres")
    private String endereco;

    @NotEmpty(message = "Lista de itens não pode estar vazia")
    @Valid
    private List<OrderItemRequestDTO> itens;

    public OrderRequestDTO() {}

    public OrderRequestDTO(String nomeCliente, String endereco, List<OrderItemRequestDTO> itens) {
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.itens = itens;
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

    public List<OrderItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<OrderItemRequestDTO> itens) {
        this.itens = itens;
    }
}
