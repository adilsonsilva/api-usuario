package br.com.ars.apiusuario;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioNotFoundException;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.repository.UsuarioRepository;
import br.com.ars.apiusuario.model.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest
@ActiveProfiles("test")
public class ApiUsuarioApplicationTests {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioRepository respository;

	@Before
	public void inicializar() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("dois@teste.com.br");
		usuarioDTO.setNome("Teste Dois");
		usuarioDTO.setSenha("12de34");

		usuarioService.cadastrarUsuario(usuarioDTO);
	}

	@After
	public void finalizar() {

		respository.deleteAll();

	}

	@Test(expected = UsuarioNotFoundException.class)
	public void testeUsuario_BuscarUsuarioPorId_NaoExistente() {
		assertNull(usuarioService.buscarUsuario(100));
	}

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

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("um@teste.com.br");
		usuarioDTO.setNome("Teste Um");
		usuarioDTO.setSenha("3344ff");

		usuarioService.cadastrarUsuario(usuarioDTO);

		UsuarioDTO usuarioDTO2 = new UsuarioDTO();
		usuarioDTO2.setAtivo(Boolean.TRUE);
		usuarioDTO2.setEmail("um@teste.com.br");
		usuarioDTO2.setNome("Teste Um");
		usuarioDTO2.setSenha("3344ff");

		usuarioService.cadastrarUsuario(usuarioDTO2);
	}

	@Test
	public void buscarUsuarioPorId() {
		assertNotNull(usuarioService.buscarUsuario(1));
	}

	@Test
	public void deletarUsuario() {

	}

}
