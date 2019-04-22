package br.com.ars.apiusuario;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioDeleteException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") 
    })
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

		UsuarioEntity ent = usuarioService.cadastrarUsuario(usuarioDTO);

		assertNotNull(ent);
	}

	@Test(expected = UsuarioPadraoSenhaException.class)
	public void testeUsuario_ValidacaoSenha_ForaPadrao() {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("cinco@teste.com.br");
		usuarioDTO.setNome("Teste Cinco");
		usuarioDTO.setSenha("@@1111");
		usuarioService.cadastrarUsuario(usuarioDTO);

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
		} catch (UsuarioDeleteException e) {
			fail();
		}

		assertTrue(Boolean.TRUE);

	}

	@Test
	public void testeUsuario_DeletarUsuario_Inexistente() {
		
		try {
			usuarioService.deletarUsuario(550);
			fail();
		}catch (UsuarioNotFoundException e) {
			assertTrue(Boolean.TRUE);
		}
	}

}
