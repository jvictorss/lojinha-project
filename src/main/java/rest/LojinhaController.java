package main.java.rest;

import main.java.infrastructure.utils.FileUtils;
import main.java.infrastructure.utils.Valid;
import main.java.repositories.repository.ComprasRepository;
import main.java.repositories.repository.ProdutoRepository;
import main.java.repositories.repository.UserRepository;
import main.java.service.ComprasService;
import main.java.service.ProdutoService;
import main.java.service.UsuarioService;
import main.java.view.Menus;

public class LojinhaController {
  private UsuarioService usuarioService;
  private ProdutoService produtoService;
  private ComprasService comprasService;
  private Menus menus;

  public LojinhaController() {
    this.usuarioService = new UsuarioService(new FileUtils(), new UserRepository(), new Valid());
    this.produtoService = new ProdutoService(new FileUtils(), new ProdutoRepository());
    this.comprasService = new ComprasService(new ComprasRepository(), produtoService, new FileUtils());
    this.menus = new Menus(usuarioService, produtoService, comprasService, new Valid());
  }

  public void iniciar() {
    menus.menuInicial();
  }
}
