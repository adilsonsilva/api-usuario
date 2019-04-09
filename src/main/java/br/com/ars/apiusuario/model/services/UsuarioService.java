package br.com.ars.apiusuario.model.services;

import java.util.List;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastroException;
import br.com.ars.apiusuario.exception.UsuarioDeleteException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.exception.UsuarioUpdateException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;

public interface UsuarioService {

	public UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO) throws UsuarioCadastroException;

	public UsuarioEntity buscarUsuario(Integer id) throws UsuarioNotFoundException;
	
	public void ativarUsuario(Integer id) throws UsuarioUpdateException, UsuarioNotFoundException;
	
	public void inativarUsuario(Integer id) throws UsuarioUpdateException, UsuarioNotFoundException;
	
	public void deletarUsuario(Integer id) throws UsuarioDeleteException, UsuarioNotFoundException;
	
	public List<UsuarioEntity> listarUsuarios();

}
