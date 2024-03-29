package main.java.repositories;


import main.java.rest.dtos.ClienteDto;
import java.util.List;

public interface UserRepositoryView {
  boolean login(String cpf, String senha, String fileName);

  boolean usuarioExiste(String cpf, String fileName);

  void bemVindoUsuario(String cpf, String fileName);

  void visualizarCadastro(String cpf, String fileName);

  void alterarCadastro(String cpf, String novoNome, String novoTelefone);

  void salvarDadosUsuario(String cpf, String senha, String userType, String fileName);

  void salvarUsuario(String cpf, String nome, String telefone, String userType, String fileName);

  void removerCadastro(String cpf);

  List<ClienteDto> visualizarCadastros();

  ClienteDto getCliente(String cpf);
}
