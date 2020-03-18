package br.com.ars.apiusuario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;
import br.com.ars.apiusuario.model.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") })
public class ApiUsuarioApplicationTests {

	@Autowired
	UsuarioService usuarioService;

	@Test
	public void testeUsuario_criar() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("quatro@teste.com.br");
		usuarioDTO.setNome("Teste Quatro");
		usuarioDTO.setSenha("12de34");

		try {
			usuarioService.cadastrarUsuario(usuarioDTO);
		} catch (Exception e) {
			fail();
		}

	}

	@Test(expected = UsuarioCadastradoException.class)
	public void testeUsuario_UsuarioJaCadastrado() {

		UsuarioDTO usuarioDTO2 = new UsuarioDTO();
		usuarioDTO2.setAtivo(Boolean.TRUE);
		usuarioDTO2.setEmail("teste@teste.com.br");
		usuarioDTO2.setNome("Adilson Silva");
		usuarioDTO2.setSenha("123456");

		usuarioService.cadastrarUsuario(usuarioDTO2);
	}

	@Test
	public void testeUsuario_BuscarUsuarioPorId() {
		assertNotNull(usuarioService.buscarUsuario(1));
	}

	@Test(expected = UsuarioNotFoundException.class)
	public void testeUsuario_BuscarUsuarioPorId_NaoExistente() {
		assertNull(usuarioService.buscarUsuario(100));
	}

	@Test
	public void testeUsuairo_DeletarUsuario() {

		try {
			usuarioService.deletarUsuario(1);
		} catch (UsuarioException e) {
			fail();
		}

	}

	@Test
	public void testeUsuario_DeletarUsuario_Inexistente() {

		try {
			usuarioService.deletarUsuario(550);
			fail();
		} catch (UsuarioNotFoundException e) {
			assertTrue(Boolean.TRUE);
		}
	}

	@Test
	public void testeUsuario_listarTodos() {
		assertNotNull(usuarioService.listarUsuarios());
	}

	// @Test
	// public void testeUsuario_criptografarSenha() {
	//
	// UsuarioDTO usuarioDTO2 = new UsuarioDTO();
	// usuarioDTO2.setAtivo(Boolean.TRUE);
	// usuarioDTO2.setEmail("teste11@teste.com.br");
	// usuarioDTO2.setNome("Adilson Silva");
	// usuarioDTO2.setSenha("123456");
	//
	// UsuarioEntity user = usuarioService.cadastrarUsuario(usuarioDTO2);
	//
	// assertNotEquals(user.getSenha(), "123456");
	// }

	@Test
	public void testeUsuario_criptografarSenha_Nulo() {

		UsuarioDTO usuarioDTO2 = new UsuarioDTO();
		usuarioDTO2.setAtivo(Boolean.TRUE);
		usuarioDTO2.setEmail("teste11@teste.com.br");
		usuarioDTO2.setNome("Adilson Silva");

		try {
			usuarioService.cadastrarUsuario(usuarioDTO2);
			fail();
		} catch (UsuarioPadraoSenhaException e) {
			assertTrue(Boolean.TRUE);
		}

	}

	@Test
	public void testeUsuario_ativar() {

		UsuarioDTO usuarioInativo = usuarioService.buscarUsuario(Integer.valueOf(4));

		boolean status = usuarioInativo.getAtivo();

		usuarioService.ativarUsuario(Integer.valueOf(4));

		UsuarioDTO usuarioPostAtivar = usuarioService.buscarUsuario(Integer.valueOf(4));

		assertFalse(status);
		assertTrue(usuarioPostAtivar.getAtivo());
	}

	@Test
	public void testeUsuario_inativar() {
		UsuarioDTO usuarioAtivo = usuarioService.buscarUsuario(Integer.valueOf(1));

		boolean status = usuarioAtivo.getAtivo();

		usuarioService.inativarUsuario(Integer.valueOf(1));

		UsuarioDTO usuarioPostInativar = usuarioService.buscarUsuario(Integer.valueOf(1));

		assertTrue(status);
		assertFalse(usuarioPostInativar.getAtivo());
	}

	@Test(expected = UsuarioNotFoundException.class)
	public void testeUsuario_naoEncontrado_ativar() {
		usuarioService.ativarUsuario(Integer.valueOf(5));
	}

	@Test(expected = UsuarioNotFoundException.class)
	public void testeUsuario_naoEncontrado_inativar() {
		usuarioService.ativarUsuario(Integer.valueOf(5));
	}

}
