package br.com.ars.apiusuario.model.services;

import java.util.List;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;

public interface UsuarioService {

	public UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO);

	public UsuarioEntity buscarUsuario(Integer id);

	public void ativarUsuario(Integer id);

	public void inativarUsuario(Integer id);

	public void deletarUsuario(Integer id);

	public List<UsuarioEntity> listarUsuarios();

}
