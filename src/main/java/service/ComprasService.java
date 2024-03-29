package main.java.service;

import main.java.infrastructure.utils.FileUtils;
import main.java.repositories.ComprasRepositoryView;
import main.java.rest.dtos.CarrinhoDto;

import java.util.List;

import static main.java.constants.Constantes.CARRINHO;
import static main.java.constants.Constantes.CARRINHO_CABECALHO;
import static main.java.infrastructure.utils.Present.print;

public class ComprasService {

  private final ComprasRepositoryView repository;
  private final ProdutoService produtoService;
  private final FileUtils fileUtils;

  public ComprasService(ComprasRepositoryView repository, ProdutoService produtoService, FileUtils fileUtils) {
    this.repository = repository;
    this.produtoService = produtoService;
    this.fileUtils = fileUtils;
    fileUtils.createIfNotExists(CARRINHO, CARRINHO_CABECALHO);
  }

  public boolean carrinhoSalvo(String cpf) {
    return repository.carrinhoAberto(cpf);
  }

  public List<CarrinhoDto> verCarrinho(String cpf) {
    print("=".repeat(70) +
        "\nJá existe um carrinho aberto para este CPF. \nVisualizando itens do carrinho.\n ");
    return repository.obterItensCarrinho(cpf);
  }

  public void imprimirCarrinho(List<CarrinhoDto> carrinho) {
    repository.imprimirCarrinho(carrinho);
  }

  public void alterarItemCarrinho(List<CarrinhoDto> carrinho, String codigo, Integer quantidade) {
    repository.alterarItemCarrinho(carrinho, codigo, quantidade);
  }

  public void salvarCarrinho(String cpf, List<CarrinhoDto> carrinho) {
    repository.salvarCarrinho(cpf, carrinho);
  }

  public void limparCarrinho(String cpf) {
    repository.limparCarrinho(cpf);
  }

  public void emitirNotaFiscal(List<CarrinhoDto> itens, String cpf, String formaPagamento) {
    repository.emitirNotaFiscal(itens, cpf, formaPagamento);
  }
}