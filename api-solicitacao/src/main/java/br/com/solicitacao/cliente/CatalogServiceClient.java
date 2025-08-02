package br.com.solicitacao.cliente;

import br.com.solicitacao.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalogo-service")
public interface CatalogServiceClient {

    @GetMapping("/Produtos")
    List<ProdutoDTO> pegaTodosProdutos();

    @GetMapping("/Produtos/{id}")
    ProdutoDTO pegarProdutoId(@PathVariable("id") Long id);
}