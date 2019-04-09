package br.com.ars.apiusuario;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ars.apiusuario.model.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiUsuarioApplicationTests {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void contextLoads() {
		assertNotNull(usuarioRepository.findById(1));
	}

}
