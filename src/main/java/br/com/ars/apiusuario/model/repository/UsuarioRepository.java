package br.com.ars.apiusuario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ars.apiusuario.model.entitys.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	@Query(value = "SELECT u FROM UsuarioEntity u WHERE u.id =:id")
	UsuarioEntity findUsuarioById(@Param(UsuarioEntity.ID) Integer id);
	
	@Modifying
	@Query(value = "UPDATE UsuarioEntity u SET u.ativo =:ativo WHERE u.id =:id")
	UsuarioEntity updateStatusUsuario(@Param(UsuarioEntity.ID) Integer id, @Param(UsuarioEntity.ATIVO) Boolean value);
	
	@Query(value="SELECT u FROM UsuarioEntity u WHERE u.email =:email")
	UsuarioEntity findUsuarioPorEmail(@Param(UsuarioEntity.EMAIL) String email);

}
