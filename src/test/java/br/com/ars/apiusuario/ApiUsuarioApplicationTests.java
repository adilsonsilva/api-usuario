package br.com.ars.apiusuario;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ars.apiusuario.model.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class ApiUsuarioApplicationTests {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void contextLoads() {
		assertNotNull(usuarioRepository.findById(1));
	}

}
