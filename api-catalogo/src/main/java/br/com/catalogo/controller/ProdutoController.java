package br.com.catalogo.controller;

import br.com.catalogo.dto.ProdutoRequestDTO;
import br.com.catalogo.dto.ProdutoResponseDTO;
import br.com.catalogo.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Produtos")
@Tag(name = "Produtos", description = "Api para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService _produtoService;

    @GetMapping
    @Operation(summary = "Lista todos produtos", description = "Retorna uma lista com todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoResponseDTO>> pegarTodosProdutos() {
        List<ProdutoResponseDTO> produtos = _produtoService.encontrarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico pelo seu ID")
    public ResponseEntity<ProdutoResponseDTO> pegarProdutoId(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        ProdutoResponseDTO produto = _produtoService.encontrarProdutoId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cria um novo produto no catálogo")
    public ResponseEntity<ProdutoResponseDTO> criarProduto(
            @Valid @RequestBody ProdutoRequestDTO produtoRequest) {
        ProdutoResponseDTO criarProduto = _produtoService.create(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza um produto existente")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO produtoRequest) {
        ProdutoResponseDTO updatedProduct = _produtoService.update(id, produtoRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove um produto do catálogo")
    public ResponseEntity<Void> deleteProduto(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        _produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
