package br.com.ars.apiusuario.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.ars.apiusuario.model.entitys.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE UsuarioEntity u SET u.ativo =:ativo WHERE u.id =:id")
	void updateStatusUsuario(@Param(UsuarioEntity.ID) Integer id, @Param(UsuarioEntity.ATIVO) Boolean value);

	Optional<UsuarioEntity> findUsuarioByEmailIgnoringCase(String email);

	Optional<UsuarioEntity> findUsuarioByEmailIgnoringCaseAndAtivoEquals(String email, Boolean ativo);

}
