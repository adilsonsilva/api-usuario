/**
 * 
 */
package br.com.ars.apiusuario.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.model.services.UsuarioService;
import br.com.ars.apiusuario.responses.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

/**
 * @author adilson
 *
 */
@Log4j2
@Api(value = "API Usuário", description = "API para consumo da entidade usuário")
@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@ApiOperation(value = "Cadastro de usuario")
	@PostMapping
	public ResponseEntity<Response<UsuarioDTO>> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {

		log.info("Cadastrando o usuario: " + usuarioDTO.toString());

		Response<UsuarioDTO> response = new Response<>();

		usuarioService.cadastrarUsuario(usuarioDTO);

		response.setData(usuarioDTO);
		response.setMensagemSucesso("Dados cadastrados com sucesso");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deletar usuário")
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deletar(@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		log.info(String.format("Inativar usuario %s", id));

		Response<Boolean> response = new Response<>();

		usuarioService.deletarUsuario(id);
		response.setData(Boolean.TRUE);
		response.setMensagemSucesso(String.format("Usuario %s deletado com sucesso", id));
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
		log.info(String.format("Inativar usuario %s", id));

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
		log.info(String.format("Ativar usuario %s", id));

		Response<Boolean> response = new Response<>();

		usuarioService.inativarUsuario(id);
		response.setData(Boolean.TRUE);
		response.setMensagemSucesso(String.format("Usuario %s ativado com sucesso", id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar usuário por Id")
	@GetMapping("/{id}")
	public ResponseEntity<Response<UsuarioDTO>> obterUsuario(
			@ApiParam(value = "Id do usuário") @PathVariable Integer id) {
		log.info("Buscando o usuario: " + id);

		Response<UsuarioDTO> response = new Response<>();

		UsuarioDTO usuairo = usuarioService.buscarUsuario(id);

		response.setData(usuairo);
		response.setMensagemSucesso("Busca realizada com sucesso");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar todos os usuários")
	@GetMapping
	public ResponseEntity<Response<List<UsuarioDTO>>> obterUsuarios() {
		log.info("Buscando todos os usuarios");
		Response<List<UsuarioDTO>> response = new Response<>();

		List<UsuarioDTO> usuairos = usuarioService.listarUsuarios();
		response.setData(usuairos);
		response.setMensagemSucesso("Busca realizada com sucesso");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
