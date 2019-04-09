package br.com.ars.apiusuario.model.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.controllers.UsuarioController;
import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioCadastroException;
import br.com.ars.apiusuario.exception.UsuarioDeleteException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;
import br.com.ars.apiusuario.exception.UsuarioUpdateException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.repository.UsuarioRepository;
import br.com.ars.apiusuario.model.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private Logger logger = LogManager.getLogger(UsuarioController.class);

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO) {

		//TODO
		isUsuarioExistente(usuarioDTO.getEmail());
		
		// TODO
		criptografarSenha(usuarioDTO.getSenha());

		try {
			UsuarioEntity entidade = obterEntidadeUsuario(usuarioDTO);
			usuarioRepository.save(entidade);
			return entidade;
		} catch (Exception e) {
			String msg = String.format(Constantes.MSG_ERRO_CADASTRAR_USUARIO, usuarioDTO.getEmail());
			logger.error(msg, e);
			throw new UsuarioCadastroException(msg);
		}
	}

	@Override
	public UsuarioEntity buscarUsuario(Integer id) {
		Optional<UsuarioEntity> user = usuarioRepository.findById(id);
		user.orElseThrow(() -> new UsuarioNotFoundException(Constantes.MSG_USUARIO_NAO_ENCONTRADO + id));
		return user.get();
	}

	@Override
	public void inativarUsuario(Integer id) {
		try {
			Optional<UsuarioEntity> user = usuarioRepository.findById(id);
			user.orElseThrow(UsuarioNotFoundException::new);
			usuarioRepository.updateStatusUsuario(id, Boolean.FALSE);
		} catch (Exception e) {
			String msg = String.format(Constantes.MSG_ERRO_INATIVAR_USUARIO, id);
			logger.error(msg, e);
			throw new UsuarioUpdateException(msg);
		}
	}

	@Override
	public void ativarUsuario(Integer id) {
		try {
			Optional<UsuarioEntity> user = usuarioRepository.findById(id);
			user.orElseThrow(UsuarioNotFoundException::new);
			usuarioRepository.updateStatusUsuario(id, Boolean.TRUE);
		} catch (Exception e) {
			String msg = String.format(Constantes.MSG_ERRO_ATIVAR_USUARIO, id);
			logger.error(msg, e);
			throw new UsuarioUpdateException(msg);
		}
	}

	@Override
	public void deletarUsuario(Integer id) {
		try {
			Optional<UsuarioEntity> user = usuarioRepository.findById(id);
			user.orElseThrow(UsuarioNotFoundException::new);
			UsuarioEntity entity = user.get();
			usuarioRepository.delete(entity);
		} catch (Exception e) {
			String msg = String.format(Constantes.MSG_ERRO_DELETE_USUARIO, id);
			logger.error(msg, e);
			throw new UsuarioDeleteException(msg);
		}
	}

	@Override
	public List<UsuarioEntity> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	/**
	 * Prove uma entidade de usuario para cadastro
	 * 
	 * @param usuarioDTO
	 *            usuarioDTO
	 * @return UsuarioEntity
	 */
	private UsuarioEntity obterEntidadeUsuario(UsuarioDTO usuarioDTO) {
		UsuarioEntity entidade = new UsuarioEntity();
		BeanUtils.copyProperties(usuarioDTO, entidade);

		LocalDateTime data = LocalDateTime.now();

		entidade.setDataCadastro(data);
		entidade.setDataExpiracao(data.plusYears(1));
		entidade.setAtivo(Boolean.TRUE);
		return entidade;
	}

	/**
	 * Verifica se usuario já existe
	 * 
	 * @param email
	 *            email
	 */
	private void isUsuarioExistente(String email) {
		UsuarioEntity en = usuarioRepository.findUsuarioPorEmail(email);
		if (en != null)
			throw new UsuarioCadastradoException(String.format(Constantes.MSG_VALIDACAO_USUARIO_JA_EXISTENTE, email));
	}

	/**
	 * Verifica se senha esta no padrão
	 * 
	 * @param senha
	 *            senha
	 */
	private String criptografarSenha(String senha) {
		if (!senha.matches("[a-zA-Z0-9]+"))
			throw new UsuarioPadraoSenhaException(Constantes.MSG_VALIDACAO_PADRAO_SENHA);
		
		//TODO criptografar senha e remover validação
		
		return "";
	}
}
