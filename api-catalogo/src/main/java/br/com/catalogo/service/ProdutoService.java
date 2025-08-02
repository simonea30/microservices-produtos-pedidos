package br.com.catalogo.service;

import br.com.catalogo.dto.ProdutoRequestDTO;
import br.com.catalogo.dto.ProdutoResponseDTO;
import br.com.catalogo.entity.Produto;
import br.com.catalogo.exception.ProdutoNotFoundException;
import br.com.catalogo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponseDTO> encontrarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO encontrarProdutoId(Long id) {
        Produto product = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com ID: " + id));
        return convertToResponseDTO(product);
    }

    public ProdutoResponseDTO create(ProdutoRequestDTO productRequest) {
        Produto product = convertToEntity(productRequest);
        Produto savedProduct = produtoRepository.save(product);
        return convertToResponseDTO(savedProduct);
    }

    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO productRequest) {
        Produto existingProduct = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com ID: " + id));

        existingProduct.setNome(productRequest.getNome());
        existingProduct.setDescricao(productRequest.getDescricao());
        existingProduct.setPreco(productRequest.getPreco());

        Produto updatedProduct = produtoRepository.save(existingProduct);
        return convertToResponseDTO(updatedProduct);
    }

    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public List<ProdutoResponseDTO> searchByName(String nome) {
        return produtoRepository.findByNome(nome)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private ProdutoResponseDTO convertToResponseDTO(Produto Produto) {
        return new ProdutoResponseDTO(
                Produto.getId(),
                Produto.getNome(),
                Produto.getDescricao(),
                Produto.getPreco(),
                Produto.getDataCriacao(),
                Produto.getDataAtualizacao());
    }

    private Produto convertToEntity(ProdutoRequestDTO ProdutoRequest) {
        return new Produto(
                ProdutoRequest.getNome(),
                ProdutoRequest.getDescricao(),
                ProdutoRequest.getPreco());
    }
}
