package br.com.ars.apiusuario.model.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.controllers.UsuarioController;
import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.repository.UsuarioRepository;
import br.com.ars.apiusuario.model.services.UsuarioService;
import br.com.ars.apiusuario.utils.Criptografia;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public void cadastrarUsuario(UsuarioDTO usuarioDTO) {

		LOGGER.info("Cadastrando usuario: " + usuarioDTO.toString());

		isUsuarioExistente(usuarioDTO.getEmail());

		UsuarioEntity entidade = obterEntidadeUsuario(usuarioDTO);
		usuarioRepository.save(entidade);

		BeanUtils.copyProperties(entidade, usuarioDTO);

	}

	@Override
	public UsuarioDTO buscarUsuario(Integer id) {
		
		UsuarioEntity user = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(Constantes.MSG_USUARIO_NAO_ENCONTRADO + id));
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		BeanUtils.copyProperties(user, usuarioDTO);
		return usuarioDTO;
	}

	@Override
	public void inativarUsuario(Integer id) {

		UsuarioEntity user = usuarioRepository.findById(id).orElseThrow(
				() -> new UsuarioNotFoundException(String.format(Constantes.MSG_USUARIO_NAO_ENCONTRADO, id)));

		usuarioRepository.updateStatusUsuario(user.getId(), Boolean.FALSE);
	}

	@Override
	public void ativarUsuario(Integer id) {

		UsuarioEntity user = usuarioRepository.findById(id).orElseThrow(
				() -> new UsuarioNotFoundException(String.format(Constantes.MSG_USUARIO_NAO_ENCONTRADO, id)));

		usuarioRepository.updateStatusUsuario(user.getId(), Boolean.TRUE);
	}

	@Override
	public void deletarUsuario(Integer id) {

		UsuarioEntity user = usuarioRepository.findById(id).orElseThrow(
				() -> new UsuarioNotFoundException(String.format(Constantes.MSG_USUARIO_NAO_ENCONTRADO, id)));

		usuarioRepository.delete(user);

	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {

		List<UsuarioDTO> usuarios = new ArrayList<>();

		Stream<UsuarioEntity> users = usuarioRepository.findAll().stream();

		users.forEach(u -> {
			UsuarioDTO dto = new UsuarioDTO();
			BeanUtils.copyProperties(u, dto);
			usuarios.add(dto);
		});

		return usuarios;
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

		Criptografia criptografia = new Criptografia();
		String senhaCriptografada = criptografia.criptografarSenha(usuarioDTO.getSenha());

		entidade.setSenha(senhaCriptografada);
		entidade.setDataCadastro(data);
		entidade.setDataExpiracao(data.plusYears(1));
		entidade.setAtivo(Boolean.TRUE);
		entidade.setAdmin(Boolean.FALSE);
		return entidade;
	}

	/**
	 * Verifica se usuario já existe
	 * 
	 * @param email
	 *            email
	 */
	private void isUsuarioExistente(String email) {
		Optional<UsuarioEntity> en = usuarioRepository.findUsuarioByEmailIgnoringCase(email);

		if (en.isPresent()) {
			throw new UsuarioCadastradoException(String.format(Constantes.MSG_VALIDACAO_USUARIO_JA_EXISTENTE, email));
		}

	}
}
