/**
 * 
 */
package br.com.ars.apiusuario.model.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author adilson
 *
 */
@Getter
@Setter
@ToString(exclude = "senha")
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "usuario_seq", initialValue = 1, allocationSize = 1)
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3179967799946245997L;

	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String EMAIL = "email";
	public static final String ATIVO = "ativo";
	public static final String DATA_CADASTRO = "dataCadastro";
	public static final String DATA_EXPIRACAO = "dataExpiracao";
	public static final String USER_ADMIN = "userAdmin";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq")
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "data_expiracao")
	private LocalDateTime dataExpiracao;

	@Column(name = "usuario_admin")
	private Boolean admin;

}
