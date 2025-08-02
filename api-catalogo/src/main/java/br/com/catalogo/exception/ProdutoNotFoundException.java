package br.com.catalogo.exception;

public class ProdutoNotFoundException extends RuntimeException {
  public ProdutoNotFoundException(String message) {
    super(message);
  }
}
