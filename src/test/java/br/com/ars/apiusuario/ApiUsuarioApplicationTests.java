package br.com.ars.apiusuario;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ars.apiusuario.dto.UsuarioDTO;
import br.com.ars.apiusuario.exception.UsuarioCadastradoException;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;
import br.com.ars.apiusuario.model.entitys.UsuarioEntity;
import br.com.ars.apiusuario.model.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest
@ActiveProfiles("test")
public class ApiUsuarioApplicationTests {

	@Autowired
	UsuarioService usuarioService;

	@Test
	public void buscaUsuarioNaoExistente() {
		assertNotNull(usuarioService.buscarUsuario(1));
	}

	@Test
	public void criarUsuario() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("teste@teste.com.br");
		usuarioDTO.setNome("Teste Unitario");
		usuarioDTO.setSenha("12de34");

		UsuarioEntity ent = usuarioService.cadastrarUsuario(usuarioDTO);

		assertTrue(ent != null);
	}
	
	@Test
	public void criarUsuarioSenhaForaPadrao() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("Ateste@teste.com.br");
		usuarioDTO.setNome("Teste Unitario");
		usuarioDTO.setSenha("@@1111");
		UsuarioEntity ent = null;
		try {
			ent = usuarioService.cadastrarUsuario(usuarioDTO);
		}catch (UsuarioPadraoSenhaException e) {
			assertNull(ent);
		}
	}
	
	@Test
	public void criarUsuarioJaCadastrado() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAtivo(Boolean.TRUE);
		usuarioDTO.setEmail("Bteste@teste.com.br");
		usuarioDTO.setNome("Teste Unitario");
		usuarioDTO.setSenha("AA1111");
		
		UsuarioDTO _usuarioDTO = new UsuarioDTO();
		_usuarioDTO.setAtivo(Boolean.TRUE);
		_usuarioDTO.setEmail("Bteste@teste.com.br");
		_usuarioDTO.setNome("Teste Unitario");
		_usuarioDTO.setSenha("AA1111");
		
		UsuarioEntity ent1 = usuarioService.cadastrarUsuario(usuarioDTO);
		
		assertNotNull(ent1);
		
		UsuarioEntity ent = null;
		try {
			ent = usuarioService.cadastrarUsuario(_usuarioDTO);
		}catch (UsuarioCadastradoException e) {
			assertNull(ent);
		}
	}

	@Test
	public void buscarUsuarioPorId() {

	}

	@Test
	public void deletarUsuario() {

	}

}
