/**
 * 
 */
package br.com.ars.apiusuario.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.services.UsuarioService;
import br.com.ars.apiusuario.responses.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author adilson
 *
 */
@Api(value = "API Usuário", description = "API para consumo da entidade usuário")
@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

	private Logger logger = LogManager.getLogger(UsuarioController.class);

	@Autowired
	UsuarioService usuarioService;

	@ApiOperation(value = "Cadastro de usuario")
	@PostMapping
	public ResponseEntity<Response<UsuarioEntity>> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bli) {

		logger.info("Cadastrando o usuario: " + usuarioDTO.toString());

		Response<UsuarioEntity> response = new Response<>();

		UsuarioEntity usuarioCriado = usuarioService.cadastrarUsuario(usuarioDTO);

		response.setData(usuarioCriado);
		response.setMensagemSucesso("Dados cadastrados com sucesso");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deletar usuário")
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deletar(@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		logger.info(String.format("Inativar usuario %s", id));

		Response<Boolean> response = new Response<>();

		usuarioService.deletarUsuario(id);
		response.setData(Boolean.TRUE);
		response.setMensagemSucesso(String.format("Usuario %s inativado com sucesso", id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Atualizar dados de usuário")
	@PutMapping
	public ResponseEntity<Response<UsuarioDTO>> alterar(@RequestBody UsuarioDTO usuarioDTO) {
		return null;
	}

	@ApiOperation(value = "Inativa usuário")
	@PutMapping("/inativar/{id}")
	public ResponseEntity<Response<Boolean>> inativarUsuario(
			@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		logger.info(String.format("Inativar usuario %s", id));

		Response<Boolean> response = new Response<>();

		usuarioService.inativarUsuario(id);
		response.setData(Boolean.TRUE);
		response.setMensagemSucesso(String.format("Usuario %s inativado com sucesso", id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Ativa usuário")
	@PutMapping("/ativar/{id}")
	public ResponseEntity<Response<Boolean>> ativarUsuario(
			@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		logger.info(String.format("Ativar usuario %s", id));

		Response<Boolean> response = new Response<>();

		usuarioService.inativarUsuario(id);
		response.setData(Boolean.TRUE);
		response.setMensagemSucesso(String.format("Usuario %s ativado com sucesso", id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar usuário por Id")
	@GetMapping("/{id}")
	public ResponseEntity<Response<UsuarioEntity>> obterUsuario(
			@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		logger.info("Buscando o usuario: " + id);

		Response<UsuarioEntity> response = new Response<>();

		UsuarioEntity usuairo = usuarioService.buscarUsuario(id);

		response.setData(usuairo);
		response.setMensagemSucesso("Busca realizada com sucesso");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar todos os usuários")
	@GetMapping
	public ResponseEntity<Response<List<UsuarioEntity>>> obterUsuarios() {
		logger.info("Buscando todos os usuarios");
		Response<List<UsuarioEntity>> response = new Response<>();

		List<UsuarioEntity> usuairos = usuarioService.listarUsuarios();
		response.setData(usuairos);
		response.setMensagemSucesso("Busca realizada com sucesso");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
