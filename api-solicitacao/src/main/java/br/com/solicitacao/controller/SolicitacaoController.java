package br.com.solicitacao.controller;

import br.com.solicitacao.dto.SolicitacaoRequestDTO;
import br.com.solicitacao.dto.SolicitacaoResponseDTO;
import br.com.solicitacao.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacao")
@Tag(name = "Solicitacao", description = "API para simulação de pedidos")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService _solicitacaoService;

    @PostMapping
    @Operation(summary = "Criar novo pedido", description = "Simula a criação de um novo pedido com base nos produtos disponíveis")
    public ResponseEntity<SolicitacaoResponseDTO> createOrder(
            @Valid @RequestBody SolicitacaoRequestDTO solicitacaoRequest) {
        SolicitacaoResponseDTO createdOrder = _solicitacaoService.createOrder(solicitacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna um pedido específico pelo seu ID")
    public ResponseEntity<SolicitacaoResponseDTO> getOrderById(
            @Parameter(description = "ID do pedido") @PathVariable String id) {
        SolicitacaoResponseDTO order = _solicitacaoService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista com todos os pedidos simulados")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAllOrders() {
        List<SolicitacaoResponseDTO> orders = _solicitacaoService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
