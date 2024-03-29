package main.java.service;

import main.java.infrastructure.utils.FileUtils;
import main.java.repositories.ProdutoRepositoryView;
import main.java.rest.dtos.ProdutoDto;

import java.util.List;

import static main.java.constants.Constantes.BD_PRODUTOS;
import static main.java.constants.Constantes.BD_PRODUTOS_CABECALHO;

public class ProdutoService {

  private final ProdutoRepositoryView repository;

  public ProdutoService(FileUtils fileUtils, ProdutoRepositoryView repository) {
    this.repository = repository;
    fileUtils.createIfNotExists(BD_PRODUTOS, BD_PRODUTOS_CABECALHO);
  }

  public void cadastrarProduto(String codigo, String descricaoProduto, Double valor, int quantidade){
    repository.cadastrarProduto(codigo, descricaoProduto, valor, quantidade);
  }

  public boolean produtoExiste(String codigo, String fileName) {
    return repository.produtoExiste(codigo, fileName);
  }

  public boolean isAvailableQuantity(String codigo, int quantidade){
    return repository.haQuantidadeDisponivel(codigo, quantidade);
  }

  public void alterarQuantidadeProduto(String codigo, Integer quantidade, String metodo){
    repository.changeQuantity(codigo, quantidade, metodo);
  }

  public List<ProdutoDto> visualizarProdutos(){
    return repository.visualizarCadastroProdutos();
  }

  public void alterarPrecoProduto(String codigo, Double novoValor){
    repository.alterarCadastroDeProduto(codigo, null, novoValor, null);
  }

  public void alterarCadastroProduto(String codigo, String novaDescricao, Double novoValor, Integer novaQuantidade){
    repository.alterarCadastroDeProduto(codigo, novaDescricao, novoValor, novaQuantidade);
  }

  public void excluirProduto(String codigo){
    repository.excluirProduto(codigo);
  }

  public ProdutoDto getProduto(String codigo) {
    return repository.getProduto(codigo);
  }

}
