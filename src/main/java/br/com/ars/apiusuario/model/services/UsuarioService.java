package br.com.ars.apiusuario.model.services;

import java.util.List;

import br.com.ars.apiusuario.dto.UsuarioDTO;

public interface UsuarioService {

	public void cadastrarUsuario(UsuarioDTO usuarioDTO);

	public UsuarioDTO buscarUsuario(Integer id);

	public void ativarUsuario(Integer id);

	public void inativarUsuario(Integer id);

	public void deletarUsuario(Integer id);

	public List<UsuarioDTO> listarUsuarios();

}
