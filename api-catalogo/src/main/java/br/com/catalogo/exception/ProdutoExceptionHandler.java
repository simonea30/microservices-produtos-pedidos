package br.com.catalogo.exception;

public class ProdutoExceptionHandler extends RuntimeException {
  public ProdutoExceptionHandler(String message) {
    super(message);
  }
}
