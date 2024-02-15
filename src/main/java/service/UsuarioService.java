package main.java.service;

import main.java.infrastructure.exception.BusinessException;
import main.java.infrastructure.utils.Valid;
import main.java.repositories.UserRepositoryView;
import main.java.infrastructure.utils.FileUtils;
import main.java.rest.dtos.ClienteDto;

import java.util.List;

import static main.java.constants.Constantes.BD_GERENTES;
import static main.java.constants.Constantes.BD_GERENTES_CABECALHO;
import static main.java.constants.Constantes.BD_USER_CABECALHO;
import static main.java.constants.Constantes.BD_USUARIOS;
import static main.java.constants.Constantes.LOGIN_GERENTES;
import static main.java.constants.Constantes.LOGIN_GERENTES_CABECALHO;
import static main.java.constants.Constantes.LOGIN_USER_CABECALHO;
import static main.java.constants.Constantes.LOGIN_USUARIOS;
import static main.java.constants.Constantes.NAO_EXISTE_USUARIO;
import static main.java.infrastructure.utils.Present.print;

public class UsuarioService {
  private FileUtils fileUtils;
  private static UserRepositoryView repository;
  private final Valid valid;

  public UsuarioService(FileUtils fileUtils, UserRepositoryView repository, Valid valid){
    this.fileUtils = fileUtils;
    this.repository = repository;
    this.valid = valid;
    fileUtils.createIfNotExists(BD_USUARIOS, BD_USER_CABECALHO);
    fileUtils.createIfNotExists(BD_GERENTES, BD_GERENTES_CABECALHO);
    fileUtils.createIfNotExists(LOGIN_USUARIOS, LOGIN_USER_CABECALHO);
    fileUtils.createIfNotExists(LOGIN_GERENTES, LOGIN_GERENTES_CABECALHO);
  }

  public boolean login(String cpf, String senha, String fileName) {
    return repository.login(cpf, senha, fileName);
  }

  public void salvarDadosAcessoUsuario(String cpf, String senha, String userType, String fileName) {
    repository.salvarDadosUsuario(cpf, senha, userType, fileName);
  }

  public void salvarUsuario(String cpf, String nome, String telefone, String userType, String fileName) {
    repository.salvarUsuario(cpf, nome, telefone, userType, fileName);
  }

  public String bemVindoUsuario(String cpf, String fileName) {
    return repository.bemVindoUsuario(cpf, fileName);
  }

  public boolean usuarioExiste(String cpf, String fileName) {
    if(!valid.isValidCpf(cpf)) throw new BusinessException("O CPF não é válido.");
    return repository.usuarioExiste(cpf, fileName);
  }

  public List<ClienteDto> recuperarCadastros() {
    return repository.visualizarCadastros();
  }

  public static ClienteDto recuperarCliente(String cpf) {
    return repository.getCliente(cpf);
  }

  public void verCadastro(String cpf, String fileName){
    if (!usuarioExiste(cpf, BD_USUARIOS)) print(NAO_EXISTE_USUARIO);
    repository.visualizarCadastro(cpf, fileName);
  }

  public void alterarCadastro(String cpf, String novoNome, String novoTelefone){
    if (!usuarioExiste(cpf, BD_USUARIOS)) print(NAO_EXISTE_USUARIO);
    repository.alterarCadastro(cpf, novoNome, novoTelefone);
  }

  public void excluirCadastro(String cpf) {
    repository.removerCadastro(cpf);
  }
}