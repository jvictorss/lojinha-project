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
  private final Menus menus;

  public LojinhaController() {
    UsuarioService usuarioService = new UsuarioService(new FileUtils(), new UserRepository(), new Valid());
    ProdutoService produtoService = new ProdutoService(new FileUtils(), new ProdutoRepository());
    ComprasService comprasService = new ComprasService(new ComprasRepository(), produtoService, new FileUtils());
    this.menus = new Menus(usuarioService, produtoService, comprasService, new Valid());
  }

  public void iniciar() {
    menus.menuInicial();
  }
}
